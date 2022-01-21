package com.want.payment.core;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 16/6/28<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public interface IPayable {
    String KEY_PAYLOAD = "pay_key_payload";


    void buy(PaymentParams params);

    void setPaymentListener(OnPaymentListener listener);
}
