package com.kennie.example.project.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

import com.airbnb.lottie
        .LottieAnimationView;
import com.kennie.example.project.MainActivity;
import com.kennie.example.project.R;
import com.kennie.example.project.base.AppActivity;
import com.kennie.example.project.config.AppConfig;
import com.kennie.library.views.widget.SlantedTextView;

/**
 * 闪屏页
 */
public class SplashActivity extends AppActivity {

    private LottieAnimationView mLottieView;
    private SlantedTextView mVersionView;

    @Override
    public int getLayoutId() {
        return R.layout.act_splash;
    }

    @Override
    public void initView() {
        mLottieView = findViewById(R.id.iv_splash_lottie);
        mVersionView = findViewById(R.id.tv_splash_version);
    }

    @Override
    public void initData() {
        // 设置动画监听
        mLottieView.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLottieView.removeAnimatorListener(this);
                MainActivity.start(getContext());
                finish();
            }
        });
        mVersionView.setText(AppConfig.getBuildType().toUpperCase());
        if (AppConfig.isDebug()) {
            mVersionView.setVisibility(View.VISIBLE);
        } else {
            mVersionView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        //禁用返回键
    }
}
