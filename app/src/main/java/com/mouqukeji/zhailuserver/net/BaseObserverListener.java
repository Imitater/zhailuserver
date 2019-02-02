package com.mouqukeji.zhailuserver.net;


import com.mouqukeji.zhailuserver.bean.ErrorBean;

public interface BaseObserverListener<T> {
    void onSuccess(T result);
    void onComplete();
    void onError(Throwable e);
    void onBusinessError(ErrorBean errorBean);
    void onReLoad();
    void onBeing();
}
