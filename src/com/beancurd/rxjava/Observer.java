package com.beancurd.rxjava;

public interface Observer<T> {

    void onSubscribe(Dispose dispose);

    void onComplete();

    void onNext(T data);


    void onError(Exception e);

}
