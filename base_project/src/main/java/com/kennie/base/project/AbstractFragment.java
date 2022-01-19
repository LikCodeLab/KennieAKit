package com.kennie.base.project;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 所有Fragment的基类
 */
public abstract class AbstractFragment extends Fragment {

    protected Activity mActivity;

    /**
     * 获取全局的context，防止使用getActivity()为空
     *
     * @param context 上下文
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(mActivity).inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(savedInstanceState);
        initView(view, savedInstanceState);
        initData();
    }


    /**
     * 初始化数据 建议在此方法内加载数据和处理布局显示数据
     */
    public void initData() {
    }


    /**
     * 初始化绑定布局LayoutID(onCreateView中需要的layoutID)
     *
     * @return layoutID
     */
    protected abstract int getLayoutId();


    /**
     * 初始化
     *
     * @param savedInstanceState savedInstanceState
     */
    protected abstract void init(Bundle savedInstanceState);

    /**
     * 初始化view
     *
     * @param view               view
     * @param savedInstanceState savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);
}
