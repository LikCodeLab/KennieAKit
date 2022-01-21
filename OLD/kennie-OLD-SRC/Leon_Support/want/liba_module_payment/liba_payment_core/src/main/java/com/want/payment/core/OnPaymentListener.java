package com.want.payment.core;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 16/6/28<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public interface OnPaymentListener {

    /**
     * Payment sucess
     *
     * @param payload
     */
    void onPaySuccess(String payload);

    /**
     * Payment error
     *
     * @param e
     * @param payload
     */
    void onPayError(PaymentException e, String payload);

    /**
     * Payment cancel
     *
     * @param payload
     */
    void onPayCancel(String payload);

    /**
     * Payment in process
     *
     * @param payload
     */
    void onPayProcess(String payload);
}
