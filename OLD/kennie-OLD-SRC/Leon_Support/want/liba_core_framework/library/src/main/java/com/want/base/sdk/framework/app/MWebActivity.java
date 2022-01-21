package com.want.base.sdk.framework.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.want.base.sdk.R;
import com.want.base.sdk.framework.app.web.MWebFragment;

import java.util.HashMap;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 16/3/22<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class MWebActivity extends MToolbarActivity {

    private static final String TAG_FRAGMENT = "tag_fragment";

    public static void start(Context context,
                             String url,
                             int... flags) {
        start(context, url, (Class<? extends Activity>) null, flags);
    }

    public static void start(Context context,
                             String url,
                             Class<? extends Activity> activityClazz,
                             int... flags) {
        Bundle bundle = new Bundle();
        bundle.putString(Extras.DATA, url);
        bundle.putInt(Extras.FLAG, 1);

        start(context, bundle, activityClazz, flags);
    }

    public static void start(Context context, String url, HashMap<String, String> headers, int... flags) {
        Bundle bundle = new Bundle();
        bundle.putString(Extras.DATA, url);
        bundle.putSerializable(Extras.DATA_1, headers);
        bundle.putInt(Extras.FLAG, 2);

        start(context, bundle, flags);
    }

    public static void start(Context context, String data, String mimeType, String encoding, int... flags) {
        Bundle bundle = new Bundle();
        bundle.putString(Extras.DATA, data);
        bundle.putString(Extras.DATA_1, mimeType);
        bundle.putString(Extras.DATA_2, encoding);
        bundle.putInt(Extras.FLAG, 3);

        start(context, bundle, flags);
    }

    public static void start(Context context,
                             String url,
                             String data,
                             String mimeType,
                             String encoding,
                             String historyUrl,
                             int... flags) {
        Bundle bundle = new Bundle();
        bundle.putString(Extras.DATA, url);
        bundle.putString(Extras.DATA_1, data);
        bundle.putString(Extras.DATA_2, mimeType);
        bundle.putString(Extras.DATA_3, encoding);
        bundle.putString(Extras.DATA_4, historyUrl);
        bundle.putInt(Extras.FLAG, 5);

        start(context, bundle, flags);
    }

    private static void start(Context context, Bundle bundle, int... flags) {
        start(context, bundle, null, flags);
    }

    private static void start(Context context,
                              Bundle bundle,
                              Class<? extends Activity> activityClazz,
                              int... flags) {
        final Intent intent;
        if (null != activityClazz) {
            intent = new Intent(context, activityClazz);
        } else {
            intent = new Intent(context, MWebActivity.class);
        }

        intent.putExtras(bundle);
        if (null != flags) {
            for (int flag : flags) {
                intent.addFlags(flag);
            }
        }

        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(onCreateView());
        final Bundle bundle;
        if (null != savedInstanceState) {
            bundle = savedInstanceState;
        } else {
            bundle = getIntent().getExtras();
        }

        final FragmentManager fm = getSupportFragmentManager();

        MWebFragment fragment = (MWebFragment) fm.findFragmentByTag(TAG_FRAGMENT);
        if (null == fragment) {
            fragment = MWebFragment.newInstance();
            fm.beginTransaction().add(R.id.content, fragment, TAG_FRAGMENT).commit();
        }

        fragment.setArguments(bundle);
    }

    protected View onCreateView() {
        return getLayoutInflater().inflate(R.layout.sdk_base_fragment_activity, null, true);
    }

    @Override
    public void onBackPressed() {
        final MWebFragment fragment =
                (MWebFragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
        if (null != fragment) {
            if (fragment.getWebView().canGoBack()) {
                fragment.getWebView().goBack();
                return;
            }
        }

        super.onBackPressed();
    }
}
