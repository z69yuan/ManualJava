package com.beancurd.rxjava;

import com.beancurd.rxjava.observable.ObservableJust;

public class Observable<T> {

    public static <E> Observable<E> just(E[] elements){
        return new ObservableJust<E>(elements);
    }



    final public void observe(Observer<T> observer) {
        subscribeActual(observer);
    }

    protected void subscribeActual(Observer<T> observer) {

    }

}
