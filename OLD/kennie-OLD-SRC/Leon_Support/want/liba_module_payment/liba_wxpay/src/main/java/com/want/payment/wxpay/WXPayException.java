package com.want.payment.wxpay;

import com.want.payment.core.PaymentException;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 7/27/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class WXPayException extends PaymentException {

    /**
     * <pre>
     *     0: 支付成功
     *     -1: 错误. 可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
     *     -2: 用户取消
     * </pre>
     */
    public int status;
    public String message;

    public WXPayException() {
    }

    public WXPayException(int status, String detailMessage) {
        super(detailMessage);
        this.status = status;
    }
}