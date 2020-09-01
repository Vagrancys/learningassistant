package com.vargancys.learningassistant.http;

import android.util.Log;

import java.net.ConnectException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

public abstract class MySubscriber<T> extends DisposableObserver<T> {
    protected abstract void onSuccess(T t);

    protected void onFinish() {
    }


    protected void onFailed(int code, String msg) {
    }

    @Override
    public void onComplete() {
        onFinish();
    }

    @Override
    public void onNext(T baseModel) {
        if (baseModel instanceof BaseBean) {
            if (((BaseBean) baseModel).getCode() == 200) {
                onSuccess(baseModel);
            } else if (((BaseBean) baseModel).getCode() == 303) {
                //比如 做token无效统一处理
//                onFailed(((BaseBean) baseModel).getCode(), ((BaseBean) baseModel).getMsg());
            } else {
                onFailed(((BaseBean) baseModel).getCode(), ((BaseBean) baseModel).getMsg());
            }
        }

    }

    @Override
    public void onError(Throwable t) {
        onFinish();
        Log.e("HttpLoggingInterceptor",t.getMessage());
        if (t instanceof ConnectException) {
            //网络连接失败
            onFailed(403, t.getMessage());
        } else if (t instanceof HttpException) {
            HttpException ex = (HttpException) t;
            onFailed(ex.code(), ex.message());
        } else {
            onFailed(405, t.getMessage());
        }
    }
}

