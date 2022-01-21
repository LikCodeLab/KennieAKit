package com.want.payment.wxpay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelpay.PayResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 16/6/28<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
@SuppressLint("Registered")
public class BaseWXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI mWXAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();

        // get appid from extdata
        final String extData = intent.getStringExtra("_wxapi_payresp_extdata");
        final String appid = Utils.getAppId(extData);

        mWXAPI = WXAPIFactory.createWXAPI(this, appid);
        mWXAPI.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mWXAPI.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        // empty
    }

    @Override
    public void onResp(BaseResp baseResp) {
        final PayResp resp = (PayResp) baseResp;

        final int errorCode = resp.errCode;
        final String extData = Utils.getExtData(resp.extData);

        switch (errorCode) {
            case 0: {
                onSuccess(extData);
                break;
            }
            case -1: {
                onError(extData);
                break;
            }

            case -2: {
                onCancel(extData);
                break;
            }
        }
        onFinished();
    }

    protected void onSuccess(String extData) {
        Utils.notifyPayResult(this, Utils.RESULT_SUCCESS, extData);
    }

    protected void onCancel(String extData) {
        Utils.notifyPayResult(this, Utils.RESULT_CANCEL, extData);
    }

    protected void onError(String extData) {
        Utils.notifyPayResult(this, Utils.RESULT_ERROR, extData);
    }

    protected void onFinished() {
        finish();
    }
}
