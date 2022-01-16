package com.kennie.indexbar;

/**
 * project : KennieAKit
 * class_name :  OnLetterIndexChangeListener
 * author : Kennie
 * date : 2022/1/16 19:15
 * desc : 选中字母的回调
 */
public interface OnLetterIndexChangeListener {


    /**
     * 手指按下和抬起会回调这里
     *
     * @param touched true|false
     */
    void onTouched(boolean touched);


    /**
     * 索引字母改变时会回调这里
     *
     * @param letter 选中的索引字符
     * @param index  RecyclerView将要滚动到的位置(-1代表未找到目标位置，则列表不用滚动)
     */
    void onLetterChanged(String letter, int index);
}

