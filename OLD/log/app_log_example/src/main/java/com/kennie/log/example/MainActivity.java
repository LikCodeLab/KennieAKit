package com.kennie.log.example;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.kennie.log.example.adapter.MainActivityPagerAdapter;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity implements MainFragment.OnActionListener {


    private MainActivityPagerAdapter mainActivityPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager pager = findViewById(R.id.pager);
        mainActivityPagerAdapter = new MainActivityPagerAdapter(this, getSupportFragmentManager());
        pager.setAdapter(mainActivityPagerAdapter);
        pager.setPageMargin((int) getResources().getDimension(R.dimen.card_padding) / 3);
        pager.setOffscreenPageLimit(2);
        CircleIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }

    @Override
    public void onAction(int id) {
        switch (id) {
            case 0:
                // 演示
                FunctionActivity.open(this);
                break;
        }
    }
}
