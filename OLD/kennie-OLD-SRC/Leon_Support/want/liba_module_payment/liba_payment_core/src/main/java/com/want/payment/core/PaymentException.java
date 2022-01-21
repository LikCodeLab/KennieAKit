package com.want.payment.core;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 7/22/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class PaymentException extends Exception {

    public PaymentException() {
    }

    public PaymentException(String detailMessage) {
        super(detailMessage);
    }

    public PaymentException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public PaymentException(Throwable throwable) {
        super(throwable);
    }
}
