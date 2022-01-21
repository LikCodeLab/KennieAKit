package com.kennie.widgets.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.kennie.widgets.example.adapter.ExampleAdapter;

import java.util.Arrays;
import java.util.List;

public class MainExampleActivity extends AppCompatActivity {

    private RecyclerView mRv;
    private ExampleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        mRv = findViewById(R.id._rv_list);
        List<String> list = Arrays.asList(getResources().getStringArray(R.array.main_array));
        mAdapter = new ExampleAdapter(list);

        mRv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainExampleActivity.this, WidgetExampleActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainExampleActivity.this, BadgeViewExampleActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });

    }
}