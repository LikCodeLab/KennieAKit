package com.pvting.liba_model_alipay;

import android.app.Activity;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;

import java.net.URLEncoder;

/**
 * <b>Project:</b> alipay_demo<br>
 * <b>Create Date:</b> 2016/3/23<br>
 * <b>Author:</b> Peiweiwei<br>
 * <b>Description:</b> <br>
 */
public class Alipay {
    // 商户PID
    public static final String PARTNER = "2088121827942873";
    // 商户收款账号
    public static final String SELLER = "ruwang@hollywant.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKkcVqC2Mje0JeYMjZQMgclo5q4WfEamtLUvn6N+bXMUSVTRVSY/AKOPyQlwVvIUOW4J0QhUKAiZVs1IIfBq+BJjg7SpKVFr8OPaHDnym/DifxCy2J5KNHbb0LJ/gDeANQM8WJ56Ry/YX7ziQZAdQ3d7zKdbE7CVg92choyASYntAgMBAAECgYBwQnibLPd/FN6HrVOasQbDHhjr/c8301hoYpnVnYqodxIK9P9SaUZSTZPF6UY+YwCQtIGHxt3gf3IoybaF82yDLDnSdgDjoA4Fn2ZyIY3ph4h6HOxwRKOxH746EUoo8BBPO2YIZ3E8TcDXq6HR2A8PcE2k1ESpvIuUV8HAo6KtAQJBANLDnNvetvz/+IK6N5A6wekcY6Lzt7/Mp+DTqHwYf++UkC/dsSV2MiXjvMewcNzfhmjj75u1nwQxbGUFeE1JNcECQQDNaBaIK+IR9UsVaCG/f+xKLA2rZDzIG/7b7Dd/HHRYqjK17N4VA58op1Yc7HNkm/zMnqByJlNrJfB4cBObxtctAkEAkuUtdX0DktAg95QsV8TsE7nqo88zuWU9eJBstJkqyeOEnyfnaJG5n/jbQV5zOy47cu2yuZbbTsPXpY0rbMpiwQJBAI3bffwrOmUmn+U31l7X7QtIo2QjxOp6kye1Wpp5v0xgtsb3wGSI7Ml5z7oH/qWUKSHzx2EuYNwWBp5W4OPrjj0CQQCeYcoogzq4ovHtu5az2ydP82Y82RAbLu1UtiWTFXvHD40ORbmobMimg2puDRlF5lXAhVJAx04LEGi797fGZetf";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_CHECK_FLAG = 2;
    private static final String COMMITTING = "8000";
    private static final String SUCCESS = "9000";

    /**
     * create the order info. 创建订单信息
     */
    private String getOrderInfo(AlipayRequestVo vo) {
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";
        orderInfo += "&out_trade_no=" + "\"" + vo.tradeNo + "\"";
        orderInfo += "&subject=" + "\"" + vo.subject + "\"";
        orderInfo += "&body=" + "\"" + vo.body + "\"";
        orderInfo += "&total_fee=" + "\"" + vo.price + "\"";
        orderInfo += "&notify_url=" + "\"" + vo.notifyUrl + "\"";
        orderInfo += "&service=\"mobile.securitypay.pay\"";
        orderInfo += "&payment_type=\"1\"";
        orderInfo += "&_input_charset=\"utf-8\"";
        orderInfo += "&it_b_pay=\"30m\"";
        orderInfo += "&return_url=\"m.alipay.com\"";
        return orderInfo;
    }

    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

    public void pay(final Activity activity,final AlipayRequestVo vo) {
        String orderInfo = getOrderInfo(vo);
        String sign = sign(orderInfo);
        sign = URLEncoder.encode(sign);
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
            PayTask alipay = new PayTask(activity);
            String result = alipay.pay(payInfo, true);
            PayResult payResult = new PayResult(result);
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            String resultStatus = payResult.getResultStatus();
            if (TextUtils.equals(resultStatus, SUCCESS)) {
                vo.callBack.onSuccess();
            } else {
                vo.callBack.onError();
            }
                vo.callBack.onFinish();
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
