package com.want.sdk;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liba_module_update.download.IDownLoad;
import com.liba_module_update.update.UpdateManager;
import com.want.base.http.HttpListener;
import com.want.base.http.HttpRequest;
import com.want.base.http.HttpResponse;
import com.want.base.http.MHttpClient;
import com.want.base.http.error.HttpError;
import com.want.base.sdk.framework.adapter.IItemCreator;
import com.want.base.sdk.framework.adapter.RecyclerAdapter;
import com.want.base.sdk.framework.app.MFragmentActivity;
import com.want.base.sdk.framework.app.core.UIRunable;
import com.want.base.sdk.framework.app.fragment.MListFragment;
import com.want.base.sdk.model.update.UpdateAgent;
import com.want.core.log.lg;
import com.want.imageloader.ImageLoader;
import com.want.imageloader.OnLoadCallback;
import com.want.sdk.order.InternalOrderHistory;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MFragmentActivity implements IDownLoad {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        testImageLoader2();
//        testRecycler();
//        testUpdate();
//        testCrash();
//        testImageLoader((ImageView) findViewById(R.id.test_image),
//                        (TextView) findViewById(R.id.test_text));
//        testListFragment();

//        MMCHttpClient httpClient = MMCHttpClient.getInstance(this);

//        login(httpClient);
    }

    private void testUpdate() {
        postDelay(new UIRunable(this) {
            @Override
            protected void runOnUiThread(Context context) {
                UpdateAgent agent = new UpdateAgent.Builder(context).onlyWithWifi(false).build();
//                agent.update(MainActivity.this);
                agent.forceUpdate(context);
//                agent.silentUpdate(context);
//                UmengUpdateAgent.silentUpdate(MainActivity.this);
            }
        }, 2000);
    }

    private void testRecycler() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        final RecyclerAdapter<String> adapter =
                new RecyclerAdapter<String>(new IItemCreator<View, String>() {
                    @Override
                    public View onCreateItem(LayoutInflater inflater, int pos, String data) {
                        return inflater.inflate(android.R.layout.activity_list_item, null);
                    }

                    @Override
                    public void onUpdateItem(View item, int pos, String data) {
                        final TextView textView = (TextView) item.findViewById(android.R.id.text1);
                        textView.setText(data);
                    }

                    @Override
                    public void onReleaseItem(View item, String data) {

                    }
                });
        recyclerView.setAdapter(adapter);
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add(String.valueOf(i));
        }

        adapter.updateData(datas);
        adapter.notifyDataSetChanged();
    }

    private void testCrash() {
        onEVEvent(this, "crash");

//        throw new RuntimeException("main thread");

        new Thread() {
            @Override
            public void run() {
                super.run();
                throw new RuntimeException("crashed");
            }
        }.start();
    }

    private void testImageLoader2() {
        final String
                url =
                "http://121.43.114.252:8888/index.php/mobileconnect/image/product/product_id/3426/image_id/3672/";
        final ImageView imageView = (ImageView) findViewById(R.id.test_image);
        final TextView textView = (TextView) findViewById(R.id.test_text);

        ImageLoader.Builder builder = new ImageLoader.Builder();
        builder
                .with(this)
                .url(url)
                .callback(new OnLoadCallback() {
                    @Override
                    public void onLoadCallback(Drawable drawable) {
//                        textView.setBackground(drawable);
                    }
                })
//                .size(50, 50)
                .loading(R.mipmap.ic_launcher)
                .error(R.mipmap.base_default_small_img)
                .view(textView)
                .build().load();

    }

    private void testImageLoader(ImageView imageView, final TextView textView) {
//        ImageLoader.get()
//                   .setConfig(new Config().setLoadingRes(R.mipmap.ic_launcher)
//                                          .setErrorRes(R.mipmap.base_default_small_img));
//        final String
//                url =
//                "http://121.43.114.252:8888/index.php/mobileconnect/image/product/product_id/3426/image_id/3672/";

//        Glide.with(this).load(url).into(imageView);
//        ImageLoader.get().with(this).load(imageView, url);
//        ImageLoader.get().with(this).load(imageView, new int[]{50, 50}, url);
//        ImageLoader.get().with(this).load(new OnLoadCallback() {
//            @Override
//            public void onLoadCallback(Drawable drawable) {
//                textView.setText("测试图片");
//                final int width = (int) (textView.getWidth() / 3f + 0.5f);
//                drawable.setBounds(0, 0, width, width);
//                textView.setCompoundDrawables(null, null, null, drawable);
//
//            }
//        }, url);
    }


    private void testListFragment() {
        final MListFragment<String> fragment = new MListFragment<String>() {
            @Override
            protected View onCreateItemView(LayoutInflater inflater, int pos, String data) {
                TextView textView = new TextView(MainActivity.this);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
                return textView;
            }

            @Override
            protected void onUpdateItemView(View view, int pos, String data) {
                final TextView textView = (TextView) view;
                textView.setText("sdk: " + pos + " - data: " + data);
            }
        };

        getSupportFragmentManager().beginTransaction().replace(android.R.id.content,
                fragment).commit();

        postDelay(new UIRunable(this) {
            /**
             * Run on the UI thread.
             *
             * @param context context.
             */
            @Override
            protected void runOnUiThread(Context context) {
                List<String> datas = new ArrayList<String>();
                for (int i = 0; i < 10; i++) {
                    datas.add(String.valueOf(i));
                }
                fragment.updateData(datas);
            }
        }, 2000);
    }

    private void login(final MHttpClient client) {
        client.request(new HttpRequest.Builder(
                "http://121.43.114.252:8888/mobileconnect/customer/login")
                .addHeader("Cookie", "mobapp_code=defapp2")
                .addParam("username", "7052026540@hollywant.com")
                .addParam("password", "123456")
                .build(), new HttpListener<String>() {
            @Override
            public void onResponse(HttpResponse httpResponse) {

            }

            @Override
            public void onSuccess(String s) {
                lg.d("http", "login success: " + s);

                Serializer serializer = new Persister();
                try {
                    InternalOrderHistory history = serializer.read(InternalOrderHistory.class, s);
                    lg.d("http", "InternalOrderHistory: " + history);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(HttpError httpError) {

            }

            @Override
            public void onFinish() {
                getOrderHistory(client);
            }
        });
    }

    private void getOrderHistory(final MHttpClient client) {
        client.request(new HttpRequest.Builder(
                        "http://121.43.114.252:8888/mobileconnect/customer/orderlist/offset/0/count/20")
                        .build()
                , new HttpListener<String>() {
                    @Override
                    public void onResponse(HttpResponse httpResponse) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        lg.d("http", "order list: " + s);
                    }

                    @Override
                    public void onError(HttpError httpError) {

                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void update(View view) {
        UpdateManager downLoadManager = new UpdateManager(this, this, true);
        downLoadManager.inspection();
    }


    @Override
    public String getCodeURL() {
        return "/app_api/update_version_api";
    }
}
