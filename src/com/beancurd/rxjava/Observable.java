package com.beancurd.rxjava;

import com.beancurd.rxjava.observable.ObservableJust;
import com.beancurd.rxjava.observable.ObservableMap;
import com.beancurd.rxjava.observable.ObservableObserveOn;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Observable<T> {

    public static <E> Observable<E> just(E[] elements){
        return new ObservableJust<E>(elements);
    }



    final public void observe(Observer<T> observer) {
        subscribeActual(observer);
    }

    protected void subscribeActual(Observer<T> observer) {

    }


    public <R> Observable<R> map(ObservableMap.Mapper<T, R> mapper) {
        return new ObservableMap<T,R>(this, mapper);
    }

    public Observable<T> observeOn(Executor executor) {
        return new ObservableObserveOn<T>(this, executor);
    }

}
