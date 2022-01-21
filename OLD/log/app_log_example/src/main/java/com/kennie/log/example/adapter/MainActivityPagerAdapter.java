package com.kennie.log.example.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.kennie.log.example.MainFragment;
import com.kennie.log.example.R;


public class MainActivityPagerAdapter extends FragmentStatePagerAdapter {


    private final Context context;

    private String[] titles;
    private String[] sub_titles;

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public String[] getSub_titles() {
        return sub_titles;
    }

    public void setSub_titles(String[] sub_titles) {
        this.sub_titles = sub_titles;
    }

    public MainActivityPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
        setTitles(context.getResources().getStringArray(R.array.function_title));
        setSub_titles(context.getResources().getStringArray(R.array.function_subtitle));
    }

    @Override
    public Fragment getItem(int position) {
        return MainFragment.newInstance(position, titles[position], sub_titles[position]);
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
