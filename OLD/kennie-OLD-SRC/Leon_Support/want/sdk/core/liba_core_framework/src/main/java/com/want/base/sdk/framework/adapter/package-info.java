/**
 * adapter包封装了基于{@link android.widget.BaseAdapter}的常用适配器。使用者使用该包下的适配器的时候，
 * 不用关心视图和数据以外的事情，适配器已经封装了对应的处理。
 * 常用方法:
 * 1. 实例化CommonAdapter, 并传入你的IViewCreator;
 * 2. 通过调用{@link oms.mmc.app.adapter.CommonAdapter#addData(java.util.List)}
 * 或{@link oms.mmc.app.adapter.CommonAdapter#updateData(java.util.List)}方法来更新你的数据;
 * 3. 调用notifyDataSetChanged()方法来通知适配器数据已经改变。
 */
package com.want.base.sdk.framework.adapter;

