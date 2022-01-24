package com.kennie.http.example.entities;

import android.support.annotation.NonNull;

import com.pandaq.rxpanda.utils.GsonUtil;
import com.pandaq.sample.entities.UserInfo;

//@AutoWired
public class User {

    private UserInfo info;
    private String group;

    @NonNull
    @Override
    public String toString() {
        return GsonUtil.gson().toJson(this);
    }
}