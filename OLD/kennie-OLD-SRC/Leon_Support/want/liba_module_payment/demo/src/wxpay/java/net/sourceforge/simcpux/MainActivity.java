package net.sourceforge.simcpux;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.want.module.payment.demo.R;
import com.want.module.payment.demo.databinding.MainActivityBinding;
import com.want.payment.PaymentAgent;
import com.want.payment.wxpay.WXPay;

/**
 * <b>Project:</b> liba_module_payment<br>
 * <b>Create Date:</b> 16/6/28<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class MainActivity extends Activity {

    private static final String WX_APPID = "wxb4ba3c02aa476ea1";

    private PaymentAgent mPaymentAgent;
    private WXPay mWxPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MainActivityBinding binding =
                DataBindingUtil.inflate(getLayoutInflater(), R.layout.main_activity, null, false);
        setContentView(binding.getRoot());

        binding.setActivity(this);

        mPaymentAgent = new PaymentAgent();
        mWxPay = mPaymentAgent.getWxPay(this, WX_APPID);
    }

    public void onClickPay(View view) {
        mPaymentAgent.wxpay(mWxPay, null);
    }
}
