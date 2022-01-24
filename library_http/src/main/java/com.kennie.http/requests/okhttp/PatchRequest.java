package com.kennie.http.requests.okhttp;

import com.kennie.http.RxPanda;
import com.kennie.http.observer.ApiObserver;
import com.kennie.http.requests.okhttp.base.HttpRequest;

import java.lang.reflect.Type;

import io.reactivex.rxjava3.core.Observable;


public class PatchRequest extends HttpRequest<PatchRequest> {
    public PatchRequest(String url) {
        super(url);
    }

    @Override
    protected <T> Observable<T> execute(Type type) {
        return mApi.patch(url, localParams)
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
        this.execute(getType(callback))
                .map(o -> (T) o)
                .subscribe(callback);
    }
}
