package com.want.base.sdk.widget;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import com.want.base.sdk.R;

public class ClearEditText extends EditText implements
        OnFocusChangeListener, TextWatcher {
    /**
     * 删除按钮的引用
     */
    public Drawable mClearDrawable;

    /**
     * 控件是否有焦点
     */
    private boolean hasFoucs;


    /**
     * 是否需要设置输入类型为手机号码 当需要设置为手机号码格式时 必须在xml中设置 inputType
     */
    private boolean isPhone;

    /**
     * 监听删除事件
     */
    private boolean isDelete;
    /**
     * 变化前长度
     */
    private int beforeLen = 0;
    /**
     * 变化后长度
     */
    private int afterLen = 0;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        // 这里构造方法也很重要，不加这个很多属性不能再XML里面定义
        this(context, attrs, android.R.attr.editTextStyle);

    }

    public ClearEditText(Context context, AttributeSet attrs,
                         int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        // 获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            // throw new
            // NullPointerException("You can add drawableRight attribute in XML");
            mClearDrawable = getResources().getDrawable(
                    R.drawable.base_selector_image_delete_red);
        }

        if (mClearDrawable.getIntrinsicWidth() != -1) {
            mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(),
                    mClearDrawable.getIntrinsicHeight());
        }

        // 默认设置隐藏图标
        setClearIconVisible(false);
        // 设置焦点改变的监听
        setOnFocusChangeListener(this);
        // 设置输入框里面内容发生改变的监听
        addTextChangedListener(this);

        //设置输入类型
        isPhone = (getInputType() == InputType.TYPE_CLASS_PHONE);
        //|| getInputType() == InputType.TYPE_CLASS_NUMBER;
        //setInputType(isPhone ? InputType.TYPE_CLASS_PHONE : InputType.TYPE_CLASS_TEXT);
        //Log.d("setInputType", isPhone + "");
        //设置输入最大长度
        //setFilters(new InputFilter[]{new InputFilter.LengthFilter(isPhone ? 13 : 10000000)});

        if (isPhone) {
            setFilters(new InputFilter[]{new InputFilter.LengthFilter(13)});
        }

    }

    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件 当我们按下的位置 在 EditText的宽度 -
     * 图标到控件右边的间距 - 图标的宽度 和 EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {

                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));

                if (touchable) {
                    this.setText("");
                }
            }
        }

        return super.onTouchEvent(event);
    }

    /**
     * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFoucs = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }

    }

    /**
     * qiangzeng160519
     * 设置删除图标
     */
    public void setClearIcon(int iconId) {

        mClearDrawable = getResources().getDrawable(iconId);

//        if (colorType == 0) {
//            mClearDrawable = getResources().getDrawable(
//                    .baseR.drawable_selector_image_delete_red);
//        } else if (colorType == 1) {
//            mClearDrawable = getResources().getDrawable(
//                    R.drawable.base_selector_image_delete_green);
//        } else {
//            mClearDrawable = getResources().getDrawable(
//                    R.drawable.base_selector_image_delete_blue);
//        }

        if (mClearDrawable.getIntrinsicWidth() != -1) {
            mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(),
                    mClearDrawable.getIntrinsicHeight());
        }
    }


    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    public void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        if (hasFoucs) {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        beforeLen = s.length();
    }

    @Override
    public void afterTextChanged(Editable s) {
        afterLen = s.length();

        boolean can = beforeLen > afterLen;//表示删除
        if (isPhone) {
            //键盘输入事件处理
            if (!can) {
                int position = ClearEditText.this.getSelectionEnd();
                if (position == 4 || position == 9) {
                    s = s.insert(position - 1, " ");
//                    s = s.replace(3, 4, " ");

//                    sb.insert(afterLen, " ");
//                    Log.d("ClearEditText:", "on sb=" + sb.toString());

//                    s.clear();
//                    s.append(sb.toString());
//                    Log.d("ClearEditText:", ",s=" + s);
//                    Log.d("ClearEditText:", "s=" + s.toString() + ",s.length=" + s.length());
                    Log.d("ClearEditText:", "selection=" + ClearEditText.this.getSelectionEnd());
                }
            } else {
                //键盘删除事件处理 136 1
                if (afterLen == 4) {
                    s = s.delete(3, 4);
                }
                if (afterLen == 9) {
                    s = s.delete(8, 9);
                }
            }
        }
    }

    /**
     * 设置晃动动画
     */
    public void setShakeAnimation() {
        this.setAnimation(shakeAnimation(3));
    }

    /**
     * 晃动动画
     *
     * @param counts 1秒钟晃动多少下
     * @return
     */
    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }

    /**
     * 晃动动画
     * <p/>
     * 1秒钟晃动多少下
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void setAnimator(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 0F, 15F, 0F);
        animator.setInterpolator(new CycleInterpolator(3));
        animator.setDuration(1000);
        animator.start();
    }

    public boolean hasFoucs() {
        return hasFoucs;
    }

    OnKeyListener listener = new OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                isDelete = true;
                return true;
            } else {
                isDelete = false;
            }
            return false;
        }
    };
}