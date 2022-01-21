package com.want.payment.wxpay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.want.core.log.lg;
import com.want.payment.core.AbsPayable;
import com.want.payment.core.PaymentParams;

import org.json.JSONException;
import org.json.JSONObject;

import static com.want.payment.core.PaymentDebug.DEBUG;
import static com.want.payment.core.PaymentDebug.TAG;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 16/6/28<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class WXPay extends AbsPayable {
    private Context mContext;
    private IWXAPI mWXAPI;
    private String mWXAppId;

    private IntentFilter mIntentFilter = new IntentFilter(Utils.RESULT_ACTION);
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (null != mPaymentListener && !TextUtils.isEmpty(action)) {
                final String extData = intent.getStringExtra(Utils.RESULT_OBJ);

                switch (intent.getIntExtra(Utils.RESULT_WHAT, 1)) {
                    case Utils.RESULT_SUCCESS: {
                        mPaymentListener.onPaySuccess(extData);
                        break;
                    }
                    case Utils.RESULT_ERROR: {
                        mPaymentListener.onPayError(new WXPayException(Utils.RESULT_ERROR, "支付错误"), extData);
                        break;
                    }
                    case Utils.RESULT_CANCEL: {
                        mPaymentListener.onPayCancel(extData);
                        break;
                    }

                    case 1:
                    default: {
                        mPaymentListener.onPayError(new WXPayException(Utils.RESULT_ERROR, "支付错误"), extData);
                        break;
                    }

                }
            }
            context.unregisterReceiver(this);
        }
    };

    public WXPay(Context context, String wxappid) {
        this.mContext = context;
        this.mWXAppId = wxappid;

        mWXAPI = WXAPIFactory.createWXAPI(context, mWXAppId);
        mWXAPI.registerApp(mWXAppId);

        context.registerReceiver(mReceiver, mIntentFilter);
    }

    @Override
    public void buy(PaymentParams params) {
        // 1) 生成预付单
        if (DEBUG) {
            lg.v(TAG, "wxpay, request pre order.");
        }

        try {
            final JSONObject object = new JSONObject(params.payload);
            wxpay(object, params);
        } catch (JSONException e) {
            e.printStackTrace();
            if (mPaymentListener != null) {
                mPaymentListener.onPayError(new WXPayException(Utils.RESULT_ERROR, "参数错误"), params.extData);
            }
        }
    }

    private void wxpay(JSONObject object, PaymentParams params) {
        if (!object.has("retcode")) {
            final String appId = mWXAppId;
            final String extData = Utils.createExtData(appId, params.extData);

            PayReq req = new PayReq();
            req.appId = object.optString("appid");
            req.partnerId = object.optString("partnerid");
            req.prepayId = object.optString("prepayid");
            req.nonceStr = object.optString("noncestr");
            req.timeStamp = object.optString("timestamp");
            req.packageValue = object.optString("package");
            req.sign = object.optString("sign");
            req.extData = extData; // optional

            if (DEBUG) {
                lg.v(TAG,
                     "wxpay, send req to weixin. req: appid=%s, partnerId=%s, prepayId=%s, nonceStr=%s, timeStamp=%s, packageValue=%s, sign=%s, extData=%s",
                     req.appId,
                     req.partnerId,
                     req.prepayId,
                     req.nonceStr,
                     req.timeStamp,
                     req.packageValue,
                     req.sign,
                     req.extData);
            }

            mWXAPI.sendReq(req);
        } else {
            lg.w(TAG, "wxpay, pre order error. " + object.optString("retmsg"));
            if (mPaymentListener != null) {
                mPaymentListener.onPayError(new WXPayException(Utils.RESULT_ERROR, "参数错误"), params.extData);
            }
        }
    }
}
