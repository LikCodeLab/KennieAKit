package com.kennie.widgets.example.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

/**
 * @项目名 KennieViews
 * @类名称 ExampleAdapter
 * @类描述
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/18 14:40
 */
public class ExampleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ExampleAdapter() {
        super(android.R.layout.simple_list_item_1);
    }

    public ExampleAdapter(@Nullable List<String> data) {
        super(android.R.layout.simple_list_item_1, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, String str) {
        holder.setText(android.R.id.text1, str);
    }
}
