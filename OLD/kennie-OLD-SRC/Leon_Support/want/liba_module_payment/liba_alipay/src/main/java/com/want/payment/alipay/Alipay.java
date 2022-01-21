package com.want.payment.alipay;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.want.core.log.lg;
import com.want.payment.core.AbsPayable;
import com.want.payment.core.OnPaymentListener;
import com.want.payment.core.PaymentParams;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import static com.want.payment.core.PaymentDebug.DEBUG;
import static com.want.payment.core.PaymentDebug.TAG;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 16/6/28<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class Alipay extends AbsPayable {
    private static final int ALIPAY_FLAG = 1;

    // 商户PID
    private String PARTNER;
    // 商户收款账号
    private String SELLER;
    // 商户私钥，pkcs8格式
    private String RSA_PRIVATE;
    // 支付宝公钥
    private String RSA_PUBLIC;

    private Activity mActivity;
    private Handler mHandler;
    private IAlipayProxy mAlipayProxy;

    public Alipay(Activity activity,
                  String partner,
                  String seller,
                  String rsa) {
        this(activity, null, partner, seller, rsa);
    }

    public Alipay(Activity activity,
                  IAlipayProxy proxy) {
        this(activity, proxy, null, null, null);
    }

    public Alipay(Activity activity,
                  IAlipayProxy proxy,
                  String partner,
                  String seller,
                  String rsa) {
        this.mActivity = activity;
        this.mAlipayProxy = proxy;
        this.PARTNER = partner;
        this.SELLER = seller;
        this.RSA_PRIVATE = rsa;

        if (DEBUG) {
            lg.v(TAG, "alipay init. partner: %s, seller: %s, rsa: %s", partner, seller, rsa);
        }
    }

    /**
     * create the order info. 创建订单信息
     */
    private String createNativeOrderInfo(String subject, String body, String price) {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";
        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";
        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";
        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";
        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";
        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";
        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";
        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";
        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";
        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";
        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";
        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";
        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";
        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";
        return orderInfo;
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    private static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

    @Override
    public void buy(PaymentParams params) {
        final PayThread thread;
        if (null == mAlipayProxy) {
            final String subject = params.title;
            final String body = params.description;
            final String price = String.valueOf(params.price);

            if (TextUtils.isEmpty(subject) || TextUtils.isEmpty(body) || TextUtils.isEmpty(price)) {
                throw new IllegalArgumentException("title or body or price must not be empty.");
            }
            if (DEBUG) {
                lg.v(TAG, "alipay params. subject: %s, body: %s, price: %s", subject, body, price);
            }

            // 1) make order info
            final String orderInfo = createNativeOrderInfo(subject, body, price);
            if (DEBUG) {
                lg.v(TAG, "alipay orderinfo not signed: %s" + orderInfo);
            }
            // 2) sign order info
            String sign = SignUtils.sign(orderInfo, RSA_PRIVATE);
            try {
                sign = URLEncoder.encode(sign, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (DEBUG) {
                lg.v(TAG, "alipay signed orderinfo: %s", sign);
            }

            // 3) make pay info
            final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();
            if (DEBUG) {
                lg.v(TAG, "alipay payinfo: %s", payInfo);
            }

            thread = new PayThread(payInfo);
        } else {
            thread = new PayThread(mAlipayProxy, params);
        }

        // 4) pay in background thread
        thread.setPriority(Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        if (DEBUG) {
            lg.v(TAG, "alipay start PayThread.");
        }
    }

    private class PayThread extends Thread {
        private IAlipayProxy proxy;
        private PaymentParams params;
        private String info;

        PayThread(String payInfo) {
            this.info = payInfo;
        }

        PayThread(IAlipayProxy proxy, PaymentParams params) {
            this.proxy = proxy;
            this.params = params;
        }

        @Override
        public void run() {
            super.run();
            if (DEBUG) {
                lg.v(TAG, "alipay thread start.");
            }

            if (null != proxy) {
                this.info = proxy.onCreatePayInfo(params);
            }

            final Activity activity = mActivity;
            final PayTask task = new PayTask(activity);
            final String result = task.pay(info, true);

            if (null == mHandler) {
                mHandler = new PayHandler(mPaymentListener);
            }

            final Handler handler = mHandler;
            final Message message = handler.obtainMessage(ALIPAY_FLAG, result);
            if (!TextUtils.isEmpty(params.payload)) {
                final Bundle bundle = new Bundle();
                bundle.putString(KEY_PAYLOAD, params.payload);
                message.setData(bundle);
            }
            message.sendToTarget();

            if (DEBUG) {
                lg.v(TAG, "alipay thread end.");
            }
        }
    }

    private static class PayHandler extends Handler {
        private OnPaymentListener listener;

        private PayHandler(OnPaymentListener listener) {
            super(Looper.getMainLooper());
            this.listener = listener;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (ALIPAY_FLAG == msg.what) {
                final Result result = new Result((String) msg.obj);
                final int status = Integer.valueOf(result.getResultStatus());

                // status
                // 9000: 支付成功
                // 6001: 用户中途取消
                //
                // 8000: 正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
                // 6004: 支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
                //
                // 4000: 订单支付失败
                // 6002: 网络连接出错
                // 其他: 其他支付错误

                final Bundle bundle = msg.getData();
                final String payload;
                if (null != bundle) {
                    payload = bundle.getString(KEY_PAYLOAD);
                } else {
                    payload = null;
                }

                switch (status) {
                    case 9000: {
                        if (DEBUG) {
                            lg.v(TAG, "alipay success");
                        }
                        listener.onPaySuccess(payload);
                        break;
                    }
                    case 6001: {
                        if (DEBUG) {
                            lg.v(TAG, "alipay cancel.");
                        }
                        listener.onPayCancel(payload);
                        break;
                    }
                    case 6004:
                    case 8000: {
                        lg.w(TAG, "alipay processing.");
                        listener.onPayProcess(payload);
                        break;
                    }
                    case 4000:
                    case 6002:
                    default: {
                        AlipayException e = new AlipayException(status, result.getResult());
                        lg.w(TAG, "alipay failed. result: " + result);
                        listener.onPayError(e, payload);
                        break;
                    }
                }
            }
        }
    }
}
