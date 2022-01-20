package com.kennie.example.project.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.kennie.example.project.MainActivity;
import com.kennie.example.project.R;
import com.kennie.example.project.base.AppActivity;

/**
 * @项目名 KennieAndroid
 * @类名称 ExampleViewActivity
 * @类描述
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/11/12 0:06
 */
public class ExampleViewActivity extends AppActivity {

    private AppCompatImageView mCircleView;
    private AppCompatImageView mRectView;

    public static void start(Context context) {
        Intent intent = new Intent(context, ExampleViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_example_view;
    }

    /**
     * initView会在启动时首先执行，建议在此方法内进行布局绑定、View初始化等操作
     */
    @Override
    public void initView() {
        mCircleView = findViewById(R.id.iv_circle);
        mRectView = findViewById(R.id.iv_rect);
    }

    /**
     * initData 会在布局加载后执行，建议在此方法内加载数据和处理布局显示数据
     */
    @Override
    public void initData() {
        // 显示圆形的 ImageView
        Glide.with(this)
                .load(R.drawable.update_app_top_bg)
                .transform(new MultiTransformation<>(new CenterCrop(), new CircleCrop()))
                .into(mCircleView);

        // 显示圆角的 ImageView
        Glide.with(this)
                .load(R.drawable.update_app_top_bg)
                .transform(new MultiTransformation<>(new CenterCrop(), new RoundedCorners((int) getResources().getDimension(R.dimen._res_dp_10))))
                .into(mRectView);
    }
}
