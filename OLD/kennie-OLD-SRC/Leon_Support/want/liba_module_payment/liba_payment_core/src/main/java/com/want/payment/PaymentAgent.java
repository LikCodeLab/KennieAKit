package com.want.payment;

import com.want.payment.core.IPayable;
import com.want.payment.core.OnPaymentListener;
import com.want.payment.core.PaymentParams;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 7/22/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class PaymentAgent {

    private IPayable mPayable;
    private OnPaymentListener mListener;
    private PaymentParams mParams;

    private PaymentAgent() {
        // hide
    }

    public static PaymentAgent newInstance() {
        return new PaymentAgent();
    }

    public PaymentAgent setPayable(IPayable payable) {
        this.mPayable = payable;
        return this;
    }

    public PaymentAgent setPaymentListener(OnPaymentListener listener) {
        this.mListener = listener;
        return this;
    }

    public PaymentAgent setParams(PaymentParams params) {
        this.mParams = params;
        return this;
    }

    public void pay() {
        mPayable.setPaymentListener(mListener);
        mPayable.buy(mParams);
    }


}
