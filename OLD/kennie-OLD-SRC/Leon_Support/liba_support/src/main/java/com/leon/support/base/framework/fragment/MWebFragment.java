package com.leon.support.base.framework.fragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.leon.support.R;
import com.leon.support.base.MFragment;

import java.util.HashMap;

import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR2;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 16/3/22<br>
 * <b>Author:</b> Leon<br>
 * <b>Description:</b>
 * 通用的内置浏览器
 * <br>
 */
public class MWebFragment extends MFragment {

    private WebView mWebView;
    private ProgressBar mProgressBar;

    public static MWebFragment newInstance() {
        return new MWebFragment();
    }

    public static MWebFragment newInstance(String url) {
        MWebFragment fragment = new MWebFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Extras.DATA, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static MWebFragment newInstance(String url, HashMap<String, String> headers) {
        MWebFragment fragment = new MWebFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Extras.DATA, url);
        bundle.putSerializable(Extras.DATA_1, headers);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static MWebFragment newInstance(String data, String mimeType, String encoding) {
        MWebFragment fragment = new MWebFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Extras.DATA, data);
        bundle.putString(Extras.DATA_1, mimeType);
        bundle.putString(Extras.DATA_2, encoding);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static MWebFragment newInstance(String url,
                                           String data,
                                           String mimeType,
                                           String encoding,
                                           String historyUrl) {
        MWebFragment fragment = new MWebFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Extras.DATA, url);
        bundle.putString(Extras.DATA_1, data);
        bundle.putString(Extras.DATA_2, mimeType);
        bundle.putString(Extras.DATA_3, encoding);
        bundle.putString(Extras.DATA_4, historyUrl);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_web_fragment, null);
    }

    @SuppressLint("NewApi")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWebView = (WebView) view.findViewById(R.id.webview);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        final WebSettings settings = mWebView.getSettings();
        onWebSettings(settings, mWebView);

        innerLoadData();
    }

    protected void onWebSettings(final WebSettings settings, final WebView webView) {
        final int sdkint = Build.VERSION.SDK_INT;

        settings.setJavaScriptEnabled(true);//启用js
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//js和android交互
//        settings.setAppCachePath("");//设置缓存的指定路径
        settings.setAllowFileAccess(true); // 允许访问文件
        settings.setAppCacheEnabled(true); //设置H5的缓存打开,默认关闭
        settings.setUseWideViewPort(true);//设置webview自适应屏幕大小
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);//设置，可能的话使所有列的宽度不超过屏幕宽度
        settings.setLoadWithOverviewMode(true);//设置webview自适应屏幕大小
        settings.setDomStorageEnabled(true);//设置可以使用localStorage
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        if (!has18()) {
            //noinspection deprecation
            settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        }

        if (sdkint >= Build.VERSION_CODES.ECLAIR_MR1) {
            new Object() {
                public void setLoadWithOverviewMode(boolean overview) {
                    settings.setLoadWithOverviewMode(overview);
                }
            }.setLoadWithOverviewMode(true);
        }

        // Build.VERSION_CODES.HONEYCOMB
        if (sdkint >= 11) {
            mWebView.setLayerType(View.LAYER_TYPE_NONE, null);
        }

        if (sdkint >= 19) {
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }

        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
//                if (mListener != null && mListener.onProgressChanged(view, newProgress)) {
//                    return;
//                }

                mProgressBar.setProgress(newProgress * 100);
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                if (null != mListener) {
//                    return mListener.shouldOverrideUrlLoading(view, url);
//                }
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (!settings.getLoadsImagesAutomatically()) {
                    settings.setLoadsImagesAutomatically(true);
                }

                // Build.VERSION_CODES.HONEYCOMB
                if (sdkint >= 11) {
                    mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                }

                super.onPageFinished(view, url);
            }
        });
    }

    private void innerLoadData() {
        final Bundle bundle = getArguments();
        if (null == bundle) {
            return;
        }

        final int size = bundle.size();
        if (0 == size) {
            // empty
        } else if (1 == size) {
            final String url = bundle.getString(Extras.DATA);
            mWebView.loadUrl(url);
        } else if (2 == size) {
            final String url = bundle.getString(Extras.DATA);
            @SuppressWarnings("unchecked")
            final HashMap<String, String> headers =
                    (HashMap<String, String>) bundle.getSerializable(Extras.DATA_1);
            mWebView.loadUrl(url, headers);
        } else if (3 == size) {
            final String data = bundle.getString(Extras.DATA);
            final String mimeType = bundle.getString(Extras.DATA_1);
            final String encoding = bundle.getString(Extras.DATA_2);
            mWebView.loadData(data, mimeType, encoding);
        } else if (5 == size) {
            final String baseUrl = bundle.getString(Extras.DATA);
            final String data = bundle.getString(Extras.DATA_1);
            final String mimeType = bundle.getString(Extras.DATA_2);
            final String encoding = bundle.getString(Extras.DATA_3);
            final String historyUrl = bundle.getString(Extras.DATA_4);
            mWebView.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
        }
    }

    public WebView getWebView() {
        return mWebView;
    }

    public WebSettings getWebSettings() {
        return mWebView.getSettings();
    }

    public  boolean has18() {
        return Build.VERSION.SDK_INT >= JELLY_BEAN_MR2;
    }

}
