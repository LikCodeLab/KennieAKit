package com.kennie.views.search;

import android.widget.EditText;
import android.widget.ImageView;

/**
 * SearchBoxView功能相关接口
 */
public interface ISearchBoxView {

    /**
     * “搜索”点击事件
     *
     * @param listener 回调接口listener
     */
    void setOnSearchTextViewClickListener(OnSearchTextViewClickListener listener);

    /**
     * 清除按钮点击事件
     *
     * @param listener 回调接口listener
     */
    void setOnClearImageViewClickListener(OnImageViewClickListener listener);

    /**
     * 获取EditText的内容
     *
     * @return EditText的内容
     */
    String getSearchContent();


    /**
     * 设置EditText的内容
     *
     * @param content content
     */
    void setSearchContent(String content);


    /**
     * 获取EditText
     *
     * @return EditText
     */
    EditText getEditText();


    /**
     * 点击搜索时的回调接口
     */

    interface OnSearchTextViewClickListener {
        /**
         * onTextViewClick
         *
         * @param input   输入框
         * @param keyword 搜索内容
         */
        void onSearchClick(EditText input, String keyword);

    }


    /**
     * 点击ImageView回调接口
     */
    interface OnImageViewClickListener {

        /**
         * onImageViewClick
         *
         * @param input     当前输入框
         * @param imageView 当前imageView
         */
        void onImageViewClick(EditText input, ImageView imageView);

    }
}
