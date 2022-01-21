package com.kennie.widgets.example;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kennie.views.CheckView;
import com.kennie.widgets.tv.CountDownTextView;


/**
 * 演示Widgets
 */
public class WidgetExampleActivity extends AppCompatActivity implements View.OnClickListener, CheckView.OnCheckedChangeListener {
    private static String TAG = WidgetExampleActivity.class.getSimpleName();

    private CountDownTextView cv_register_countdown;
    private CheckView checkView1, checkView2, checkView3, checkView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_example);
        cv_register_countdown = findViewById(R.id.cv_register_countdown);
        cv_register_countdown.setOnClickListener(this);

        checkView1 = findViewById(R.id.main_activity_check_view1);
        checkView2 = findViewById(R.id.main_activity_check_view2);
        checkView3 = findViewById(R.id.main_activity_check_view3);
        checkView4 = findViewById(R.id.main_activity_check_view4);
        checkView1.setOnClickListener(this);
        checkView2.setOnClickListener(this);
        checkView3.setOnClickListener(this);
        checkView4.setOnClickListener(this);
        checkView1.setOnCheckedChangeListener(this);
        checkView2.setOnCheckedChangeListener(this);
        checkView3.setOnCheckedChangeListener(this);
        checkView4.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.cv_register_countdown) {
            cv_register_countdown.setTotalTime(10);
            cv_register_countdown.start();
        } else if (id == R.id.main_activity_check_view1) {
            checkView1.toggle(true);
        } else if (id == R.id.main_activity_check_view2) {
            checkView2.toggle(true);
        } else if (id == R.id.main_activity_check_view3) {
            checkView3.toggle();
        } else if (id == R.id.main_activity_check_view4) {
            checkView4.toggle(true);
        }
    }

    @Override
    public void onCheckedChanged(CheckView CheckView, boolean isChecked) {
        Toast.makeText(this, "checkViewId：" + CheckView + " isChecked: " + isChecked, Toast.LENGTH_SHORT).show();
    }
}