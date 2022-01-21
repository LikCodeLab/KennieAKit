package com.want.payment.core;

import android.content.Context;
import android.widget.Toast;

import com.want.module.payment.core.R;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 7/22/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class SimplePaymentListener implements OnPaymentListener {

    private Context mContext;

    public SimplePaymentListener(Context context) {
        this.mContext = context;
    }

    protected void onNotifySuccess() {
        Toast.makeText(mContext, R.string.payment_result_success, Toast.LENGTH_SHORT).show();
    }

    protected void onNotifyError() {
        Toast.makeText(mContext, R.string.payment_result_failed, Toast.LENGTH_SHORT).show();
    }

    protected void onNotifyCancel() {
        Toast.makeText(mContext, R.string.payment_result_cacel, Toast.LENGTH_SHORT).show();
    }

    protected void onNotifyProcess() {
        Toast.makeText(mContext, R.string.payment_result_process, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaySuccess(String payload) {
        onNotifySuccess();
    }

    @Override
    public void onPayError(PaymentException e, String payload) {
        onNotifyError();
    }

    @Override
    public void onPayCancel(String payload) {
        onNotifyCancel();
    }

    @Override
    public void onPayProcess(String payload) {
        onNotifyProcess();
    }
}
