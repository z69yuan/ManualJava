package com.beancurd.rxjava;

import com.beancurd.rxjava.observable.ObservableJust;
import com.beancurd.rxjava.observable.ObservableMap;

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

}
