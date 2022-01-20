package com.kennie.example.project.base;

import com.kennie.base.project.BaseActivity;
import com.kennie.example.project.R;

public abstract class AppActivity extends BaseActivity {

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim._res_activity_left_in, R.anim._res_activity_left_out);
    }
}
