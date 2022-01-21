package com.kennie.base.project.app;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.kennie.base.project.BaseTools;

/**
 * @项目名 KennieProject
 * @类名称 InitializeService
 * @类描述 启动app后初始化工具类 优化
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/9 22:55
 */
public class InitializeService extends IntentService {
    private static final String TAG = InitializeService.class.getSimpleName();
    private static final String ACTION_INIT_WHEN_APP_CREATE = "com.kennie.base.project.service.action.INIT";

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action)) {
                performInit();
            }
        }
    }

    private void performInit() {
        Log.i(TAG, "初始化 任务启动");
        BaseTools.instance().create(getApplication());
    }
}
