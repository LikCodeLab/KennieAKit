package com.want.payment.alipay;

import com.want.payment.core.PaymentParams;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 7/21/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public interface IAlipayProxy {

    String onCreatePayInfo(PaymentParams params);

}
