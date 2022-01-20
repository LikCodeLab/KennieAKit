package com.kennie.example.lib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class FunctionActivity extends AppCompatActivity {

    public static void open(Context context) {
        context.startActivity(new Intent(context, FunctionActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
    }
}