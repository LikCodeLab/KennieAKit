package com.leon.social.core.impl.weixin.wxapi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import com.leon.social.core.Configuration;
import com.leon.social.core.PlatformException;
import com.leon.social.core.PlatformFactory;
import com.leon.social.core.platform.IPlatform;
import com.leon.social.core.shareable.AbsShareable;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


/**
 * <b>Project:</b> liba_social_core<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 *
 * <br>
 */
@SuppressLint("Registered")
public class SocialWXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI mWXapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Configuration configuration =
                PlatformFactory.getConfiguration(this, IPlatform.Name.WEIXIN_CHAT);
        final String appid = configuration.get(Configuration.TAG_APPID);
        mWXapi = WXAPIFactory.createWXAPI(this, appid, false);
        mWXapi.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    protected void onFinished() {
        finish();
    }

    @Override
    public void onResp(BaseResp baseResp) {
        final int code = baseResp.errCode;
        switch (code) {
            case BaseResp.ErrCode.ERR_OK: {
                onSuccess();
                break;
            }
            case BaseResp.ErrCode.ERR_USER_CANCEL: {
                onCancel();
                break;
            }
            case BaseResp.ErrCode.ERR_SENT_FAILED:
            case BaseResp.ErrCode.ERR_UNSUPPORT:
            case BaseResp.ErrCode.ERR_AUTH_DENIED: {
                onError(new PlatformException(baseResp.errStr));
                break;
            }
        }
        onFinished();
    }

    protected void onSuccess() {
        AbsShareable.notifySuccess(this);
    }

    protected void onError(Throwable e) {
        AbsShareable.notifyError(this, e);
    }

    protected void onCancel() {
        AbsShareable.notifyCancel(this);
    }
}
