package cn.lukas.library.utils;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * @author likun
 * @classname AppStore
 * @description  浏览器工具
 * @Date 2020/4/15
 * @History 2020/4/15 author: description:
 */
public class BrowserUtils {

    /**
     * 打开浏览器搜索指定内容
     *
     * @param search 需要搜索的内容
     */
    public static void startBrowserBySearch(Context context, String search) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, search);
        context.startActivity(intent);
    }

    /**
     * 打开指定页面
     *
     * @param url 链接
     */
    public static void startBrowser(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }
}
