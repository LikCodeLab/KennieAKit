package com.want.sdk.location;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pvting.liba_model_alipay.Alipay;
import com.pvting.liba_model_alipay.AlipayRequestVo;
import com.pvting.liba_model_alipay.CallBack;
import com.want.base.sdk.framework.app.MFragmentActivity;
import com.want.location.ILocation;
import com.want.location.ILocationClient;
import com.want.location.LocationListener;
import com.want.location.LocationManager;
import com.want.location.gd.GDLocationClient;
import com.want.sdk.R;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/2<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class LocationActivity extends MFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        final TextView resultText = (TextView) findViewById(R.id.location_result);

        LocationManager.init(new GDLocationClient(this));
        final LocationManager manager = LocationManager.getInstance();
        manager.addLocationListener(new LocationListener() {
            @Override
            public void onReceiveLocation(ILocation location, ILocationClient.RESULT result) {
                resultText.append(location.getCity());
                resultText.append("\n");
                resultText.append(location.getCityCode());
                resultText.append("\n");
                resultText.append(location.getCountryCode());
                resultText.append("\n");
                resultText.append(location.getProvince());
                resultText.append("\n");
                resultText.append(location.getStreet());
                resultText.append("\n");
                resultText.append(location.getLatitude() + "");
                resultText.append("\n");
                resultText.append(location.getLongitude() + "");
                resultText.append("\n");
            }
        });

        findViewById(R.id.location_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.requestLocationUpdate();
            }
        });
    }

    /**
     * 测试支付宝支付
     * @param v
     */
    public void pay(View v){
        new Alipay().pay(this, new AlipayRequestVo("订单简述", "没有详细", "0.01", "123456", "http://www.baidu.com", new CallBack() {
            @Override
            public void onError() {
                System.out.print("error");
            }

            @Override
            public void onSuccess() {
                System.out.print("success");
            }

            @Override
            public void onFinish() {
                System.out.print("finish");
            }
        }));
    }


}
