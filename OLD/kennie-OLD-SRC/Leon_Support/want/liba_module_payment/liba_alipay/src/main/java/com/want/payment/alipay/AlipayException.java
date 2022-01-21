package com.want.payment.alipay;

import com.want.payment.core.PaymentException;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 7/22/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class AlipayException extends PaymentException {

    /**
     * <pre>
     *     9000: 支付成功
     *     6001: 用户中途取消
     *     8000: 正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
     *     6004: 支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
     *     4000: 订单支付失败
     *     6002: 网络连接出错
     *     其他: 其他支付错误
     * </pre>
     */
    public int status;
    public String message;

    public AlipayException() {
    }

    public AlipayException(int status, String detailMessage) {
        super(detailMessage);
        this.status = status;
    }
}
