package com.kennie.module.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kennie.base.project.BaseActivity;
import com.kennie.module.main.databinding.ActSplashBinding;
import com.kennie.router.RouterPath;

/**
 * @项目名 KennieAndroidProject
 * @类名称 SplashActivity
 * @类描述 App启动页
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/12/25 15:08
 */
@Route(path = RouterPath.Main.PAGER_MAIN)
public class SplashActivity extends BaseActivity {

    private ActSplashBinding actSplashBinding;


    @Override
    public int getLayoutId() {
        return R.layout.act_splash;
    }

    @Override
    public void initData() {
        super.initData();
        actSplashBinding.ivSplashLottie.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                actSplashBinding.ivSplashLottie.removeAnimatorListener(this);
            }
        });
        actSplashBinding.tvSplashVersion.setText(BuildConfig.BUILD_TYPE.toLowerCase());
        if (BuildConfig.DEBUG) {
            actSplashBinding.tvSplashVersion.setVisibility(View.VISIBLE);

        } else {
            actSplashBinding.tvSplashVersion.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        //禁用返回键
    }
}
