package com.kennie.base.project.interfaces;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;

public interface IGlobalConfig {

    /**
     * 标题栏高度
     *
     * @return int
     */
    @DimenRes
    int getTitleHeight();

    /**
     * 标题栏返回按钮资源
     *
     * @return int
     */
    @DrawableRes
    int getTitleBackIcon();

    /**
     * 标题栏背景颜色
     *
     * @return int
     */
    @ColorRes
    int getTitleBackground();

    /**
     * 标题栏文本颜色
     *
     * @return int
     */
    @ColorRes
    int getTitleTextColor();

    /**
     * 标题栏下方是否需要横线
     *
     * @return boolean
     */
    boolean isShowTitleLine();

    /**
     * 标题栏下方横线颜色
     *
     * @return int
     */
    @ColorRes
    int getTitleLineColor();

    /**
     * 刷新每页加载个数
     *
     * @return int
     */
    int getPageSize();

    /**
     * 刷新起始页的index(有些后台设置的0,有些后台设置1)
     *
     * @return int
     */
    int getStartPageIndex();
}
