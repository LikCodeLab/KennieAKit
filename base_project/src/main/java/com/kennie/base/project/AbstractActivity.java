package com.kennie.base.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kennie.base.project.action.IActivityAction;
import com.kennie.base.project.action.IHandlerAction;


/**
 * 所有Activity的基类
 */
public abstract class AbstractActivity extends AppCompatActivity implements IActivityAction, IHandlerAction {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayoutId();
        init(savedInstanceState);
        initView();
        initData();
    }

    protected void initLayoutId() {
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        } else {
            throw new NullPointerException("should be initialized setContentView() in Activity.");
        }
    }


    /**
     * 初始化View 建议在此方法内进行布局绑定、View初始化等操作
     */
    public void initView() {
    }

    /**
     * 初始化数据 建议在此方法内加载数据和处理布局显示数据
     */
    public void initData() {
    }


    /**
     * 如果当前的 Activity（singleTop 启动模式） 被复用时会回调
     *
     * @param intent intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // 设置为当前的 Intent，避免 Activity 被杀死后重启 Intent 还是最原先的那个
        setIntent(intent);
    }


    /**
     * 初始化绑定布局LayoutID(onCreateView中需要的layoutID)
     *
     * @return layoutID
     */
    public abstract int getLayoutId();

    /**
     * 初始化
     *
     * @param savedInstanceState savedInstanceState
     */
    protected abstract void init(Bundle savedInstanceState);

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeCallbacks();
    }
}
