package com.want.xrecyclerview;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.want.xrecyclerview.progressindicator.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ArrowRefreshHeader extends LinearLayout implements BaseRefreshHeader{

    private static final String TAG = "ArrowRefreshHeader";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private LinearLayout mContainer;
    private ImageView mArrowImageView;
    private SimpleViewSwithcer mProgressBar;
    private TextView mStatusTextView;
    /** 正常状态*/
    private int mState = STATE_NORMAL;
    private Context mContext;

    private TextView mHeaderTimeView;
    private Animation mRotateUpAnim;
    private Animation mRotateDownAnim;
    private Animation mRefreshAnim;
    private final int ROTATE_ANIM_DURATION = 180;
	public int mMeasuredHeight;

    public ArrowRefreshHeader(Context context) {
		super(context);
		initView(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public ArrowRefreshHeader(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	private void initView(Context context) {

        mContext = context;
		// 初始情况，设置下拉刷新view高度为0
		mContainer = (LinearLayout) LayoutInflater.from(context).inflate(
				R.layout.listview_header, null);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 0);
		this.setLayoutParams(lp);
        this.setPadding(0, 0, 0, 0);

		addView(mContainer, new LayoutParams(LayoutParams.MATCH_PARENT, 0));
		setGravity(Gravity.BOTTOM);

		mArrowImageView = (ImageView)findViewById(R.id.listview_header_arrow);
		mStatusTextView = (TextView)findViewById(R.id.refresh_status_textview);

        //init the progress view
		mProgressBar = (SimpleViewSwithcer)findViewById(R.id.listview_header_progressbar);
        AVLoadingIndicatorView progressView = new  AVLoadingIndicatorView(context);
        progressView.setIndicatorColor(0xffB5B5B5);
        progressView.setIndicatorId(ProgressStyle.BallSpinFadeLoader);
        mProgressBar.setView(progressView);


		mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateUpAnim.setFillAfter(true);
		mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateDownAnim.setFillAfter(true);


        mRefreshAnim = new RotateAnimation(0.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRefreshAnim.setDuration(100);
        mRefreshAnim.setRepeatMode(Animation.RESTART);
        mRefreshAnim.setRepeatCount(Integer.MAX_VALUE);



		mHeaderTimeView = (TextView)findViewById(R.id.last_refresh_time);
		measure(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
		mMeasuredHeight = getMeasuredHeight();
	}

    public void setProgressStyle(int style) {
        if(style == ProgressStyle.SysProgress){
            mProgressBar.setView(new ProgressBar(mContext, null, android.R.attr.progressBarStyle));
        }else{
            AVLoadingIndicatorView progressView = new  AVLoadingIndicatorView(this.getContext());
            progressView.setIndicatorColor(0xffB5B5B5);
            progressView.setIndicatorId(style);
            mProgressBar.setView(progressView);
        }
    }

    public void setArrowImageView(int resid){
        mArrowImageView.setImageResource(resid);
    }


    /**
     * 设置当前的状态：
     * 问题：［在这里设置下拉时间的文字会有一定的延迟（第一次进入页面＆＆第一次下拉的的时候）］
     *
     * @param state　窗台
     */
    public void setState(int state) {
        switch (state) {

            /** [正常状态]*/
            case STATE_NORMAL:
                //if (mState == STATE_RELEASE_TO_REFRESH) {
                //mArrowImageView.startAnimation(mRotateDownAnim);
                //}
                //if (mState == STATE_REFRESHING) {
                //   mArrowImageView.clearAnimation();
                //}
                mArrowImageView.clearAnimation();
                mStatusTextView.setText(R.string.listview_header_hint_normal);
                break;

            /** 从[正常状态] 到 [下拉状态] 但是没有到达 [刷新状态]（未执行动画）*/
            case STATE_RELEASE_TO_REFRESH:
                //if (mState != STATE_RELEASE_TO_REFRESH) {
                //  mArrowImageView.clearAnimation();
                //  mArrowImageView.startAnimation(mRotateUpAnim);
                //  mStatusTextView.setText(R.string.listview_header_hint_release);
                //}
                mArrowImageView.clearAnimation();
                break;

            /** [刷新状态]（动画执行ing）*/
            case STATE_REFRESHING:
                mArrowImageView.clearAnimation();
                mArrowImageView.startAnimation(mRefreshAnim);
                mStatusTextView.setText(R.string.refreshing);
                break;

            /** [刷新完成状态]*/
            case STATE_DONE:
                mStatusTextView.setText(R.string.refresh_done);
                mArrowImageView.clearAnimation();
                break;

            default:
                mArrowImageView.setVisibility(View.VISIBLE);
                break;
        }

        mState = state;
    }


    public int getState() {
        return mState;
    }


    /**
     * 刷新完成的回调函数 这里保存时间到sp中去　
     */
    @Override
    public void refreshComplete() {
        setState(STATE_DONE);
        new Handler().postDelayed(new Runnable(){
            public void run() {
                reset();
            }
        }, 200);
        /** 格式化时间*/
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        /** 这里保存当前时间 到sp中*/
        TextHelp.saveTimestamp(mContext, XRecyclerView.timestampKey, sdf.format(new Date()));
	}


	public void setVisiableHeight(int height) {
		if (height < 0)
			height = 0;
		LayoutParams lp = (LayoutParams) mContainer
				.getLayoutParams();
		lp.height = height;
		mContainer.setLayoutParams(lp);
	}

	public int getVisiableHeight() {
        int height = 0;
        LayoutParams lp = (LayoutParams) mContainer
                .getLayoutParams();
        height = lp.height;
		return height;
	}


    /**
     * 滑动时候状态的设置，时间的显示 和 设置
     * 修改 by LiDuo at 2016/05/26
     */
    @Override
    public void onMove(float delta) {
        if (getVisiableHeight() > 0 || delta > 0) {
            setVisiableHeight((int) delta + getVisiableHeight());
            /** 只要是有滑动动作就要去设置 HeaderTimeView 的文本（此处有坑）
             * 　１．设置时间:   不能在＂STATE_RELEASE_TO_REFRESH＂｛从[正常状态] 到 [下拉状态] 但是没有到达 [刷新状态]｝该状态下设置;
             *      产出的问题： 第一次进入页面，第一次下拉刷新的时候,文本显示会延迟;
             *      问题原因：   因为下面有距离的判断．
             *
             * 　２．保存时间可以在＂STATE_DONE＂［刷新完成状态］完成．
             */

            /** 设置HeaderTimeView时间 */
            setHeaderTimeViewContent();

            if (mState <= STATE_RELEASE_TO_REFRESH) { // 未处于刷新状态，更新箭头
                if (getVisiableHeight() > mMeasuredHeight) {
                    setState(STATE_RELEASE_TO_REFRESH);

                } else {
                    setState(STATE_NORMAL);
                }
            }
        }

    }


    /**
     * 设置HeaderTimeView时间
     * add by LiDuo at 2016/05/26
     */
    private void setHeaderTimeViewContent() {
        /** 格式化时间*/
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String dateCurrent = sdf.format(new Date());
        mHeaderTimeView.setText(dateCurrent);
        /** 从sp中拿到上次记录的时间*/
        String dateLast = TextHelp.getTimestamp(mContext, XRecyclerView.timestampKey);
        /** 如果拿到的 时间为空，那么给个默认当前时间，比如满足下面三个条件： 第一次安装软件的时候 && 第一次进入页面 && 第一次刷新 */
        if (TextUtils.isEmpty(dateLast)) {
            mHeaderTimeView.setText(dateCurrent);

        } else {
            mHeaderTimeView.setText(dateLast);
        }

    }

    @Override
    public boolean releaseAction() {
        boolean isOnRefresh = false;
        int height = getVisiableHeight();
        if (height == 0) // not visible.
            isOnRefresh = false;

        if(getVisiableHeight() > mMeasuredHeight &&  mState < STATE_REFRESHING){
            setState(STATE_REFRESHING);
            isOnRefresh = true;
        }
        // refreshing and header isn't shown fully. do nothing.
        if (mState == STATE_REFRESHING && height <=  mMeasuredHeight) {
            //return;
        }
        int destHeight = 0; // default: scroll back to dismiss header.
        // is refreshing, just scroll back to show all the header.
        if (mState == STATE_REFRESHING) {
            destHeight = mMeasuredHeight;
        }
        smoothScrollTo(destHeight);

        return isOnRefresh;
    }

    public void reset() {
        smoothScrollTo(0);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                setState(STATE_NORMAL);
            }
        }, 500);
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void smoothScrollTo(int destHeight) {
        ValueAnimator animator = ValueAnimator.ofInt(getVisiableHeight(), destHeight);
        animator.setDuration(300).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                setVisiableHeight((int) animation.getAnimatedValue());
            }
        });
        animator.start();
    }

    public static String friendlyTime(Date time) {
        //获取time距离当前的秒数
        int ct = (int)((System.currentTimeMillis() - time.getTime())/1000);

        if(ct == 0) {
            return "刚刚";
        }

        if(ct > 0 && ct < 60) {
            return ct + "秒前";
        }

        if(ct >= 60 && ct < 3600) {
            return Math.max(ct / 60,1) + "分钟前";
        }
        if(ct >= 3600 && ct < 86400)
            return ct / 3600 + "小时前";
        if(ct >= 86400 && ct < 2592000){ //86400 * 30
            int day = ct / 86400 ;
            return day + "天前";
        }
        if(ct >= 2592000 && ct < 31104000) { //86400 * 30
            return ct / 2592000 + "月前";
        }
        return ct / 31104000 + "年前";
    }

}
