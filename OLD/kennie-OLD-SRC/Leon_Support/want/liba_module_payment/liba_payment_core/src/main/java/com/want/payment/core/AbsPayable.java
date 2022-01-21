package com.want.payment.core;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 7/22/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public abstract class AbsPayable implements IPayable {

    protected OnPaymentListener mPaymentListener;

    @Override
    public void setPaymentListener(OnPaymentListener listener) {
        this.mPaymentListener = listener;
    }

    protected OnPaymentListener getPaymentListener() {
        return this.mPaymentListener;
    }
}
