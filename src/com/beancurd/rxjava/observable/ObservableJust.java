package com.beancurd.rxjava.observable;

import com.beancurd.rxjava.Dispose;
import com.beancurd.rxjava.Observable;
import com.beancurd.rxjava.Observer;
import com.beancurd.rxjava.dispose.DisposeJust;

public class ObservableJust<T> extends Observable<T> {

    private final T[] mElements;


    public ObservableJust(T[] elements) {
        this.mElements = elements;
    }


    @Override
    protected void subscribeActual(Observer<T> observer) {
        Dispose d = new DisposeJust();
        try {
            observer.onSubscribe(d);
            int len = mElements.length;
            for (int i = 0; i < len; i++) {
                // 暂时break吧
                if (d.isCancelled()) {
                    break;
                }
                observer.onNext(mElements[i]);
            }
            observer.onComplete();
        } catch (Exception e) {
            observer.onError(e);
        }

    }
}
