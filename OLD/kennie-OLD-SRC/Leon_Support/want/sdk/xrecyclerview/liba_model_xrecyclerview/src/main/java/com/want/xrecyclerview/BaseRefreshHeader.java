package com.want.xrecyclerview;

/**
 * Created by jianghejie on 15/11/22.
 * add explain by LiDuo
 */
interface BaseRefreshHeader {
    void onMove(float delta);

    /** 释放动作*/
    boolean releaseAction();
    /** 刷新完成的回调函数--Complete*/
    void refreshComplete();
    /** [正常状态]*/
    int STATE_NORMAL = 0;
    /** 从[正常状态] 到 [下拉状态] 但是没有到达 [刷新状态]（未执行动画）*/
    int STATE_RELEASE_TO_REFRESH = 1;
    /** [刷新状态]（动画执行ing）*/
    int STATE_REFRESHING = 2;
    /** [刷新完成状态]*/
    int STATE_DONE = 3;
}
