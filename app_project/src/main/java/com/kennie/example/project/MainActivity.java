package com.kennie.example.project;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.kennie.example.project.base.AppActivity;
import com.kennie.utils.helper.DoubleClickHelper;
import com.kennie.utils.manager.ActivityManager;


public class MainActivity extends AppActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onBackPressed() {
        if (!DoubleClickHelper.isOnDoubleClick()) {
            Toast.makeText(this, R.string.home_exit_hint, Toast.LENGTH_LONG).show();
            return;
        }
        // 移动到上一个任务栈，避免侧滑引起的不良反应
        moveTaskToBack(false);
        postDelayed(() -> {
            // 进行内存优化，销毁掉所有的界面
            ActivityManager.getInstance().finishAllActivities();
            // 销毁进程（注意：调用此 API 可能导致当前 Activity onDestroy 方法无法正常回调）
            // System.exit(0);
        }, 300);
    }
}