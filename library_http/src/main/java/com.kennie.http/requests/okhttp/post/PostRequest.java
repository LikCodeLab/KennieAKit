package com.kennie.http.requests.okhttp.post;


import com.kennie.http.RxPanda;
import com.kennie.http.observer.ApiObserver;
import com.kennie.http.requests.okhttp.base.HttpRequest;

import java.lang.reflect.Type;

import io.reactivex.rxjava3.core.Observable;

/**
 * <p>
 * Description :a sample request
 */
public class PostRequest extends HttpRequest<PostRequest> {

    // url中带参数 post
    private final StringBuilder stringBuilder = new StringBuilder();


    public PostRequest(String url) {
        super(url);
    }

    @Override
    protected <T> Observable<T> execute(Type type) {
        if (stringBuilder.length() != 0) {
            url = url + stringBuilder.toString();
        }
        return mApi.post(url, localParams)
                .doOnSubscribe(disposable -> {
                    if (tag != null) {
                        RxPanda.manager().addTag(tag, disposable);
                    }
                })
                .compose(httpTransformer(type));
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T> void execute(ApiObserver<T> callback) {
        if (tag != null) {
            RxPanda.manager().addTag(tag, callback);
        }
        //获取到callback 中的泛型类型
        this.execute(getType(callback))
                .map(o -> (T) o)
                .subscribe(callback);
    }

    /**
     * post url 中添加参数
     *
     * @param paramKey   key
     * @param paramValue value
     * @return self
     */
    public PostRequest urlParams(String paramKey, String paramValue) {
        if (paramKey != null && paramValue != null) {
            if (stringBuilder.length() == 0) {
                stringBuilder.append("?");
            } else {
                stringBuilder.append("&");
            }
            stringBuilder.append(paramKey).append("=").append(paramValue);
        }
        return this;
    }
}
