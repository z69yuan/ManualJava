package com.beancurd.rxjava.observable;

import com.beancurd.rxjava.Dispose;
import com.beancurd.rxjava.Observable;
import com.beancurd.rxjava.Observer;
import com.beancurd.rxjava.dispose.DisposeJust;

import java.util.concurrent.Executor;

public class ObservableObserveOn<T> extends Observable<T> {

    private Observable<T> mUp;

    private Executor mExecutor;

    public ObservableObserveOn(Observable<T> up, Executor executor) {
        this.mUp = up;
        this.mExecutor = executor;
    }


    @Override
    protected void subscribeActual(Observer<T> observer) {
        mUp.observe(new ObserveOnObserver<T>(mExecutor, observer));
    }


    public static class ObserveOnObserver<T> implements Observer<T>{
        private Observer<T> mObserver;

        private Executor mExecutor;
        public ObserveOnObserver(Executor executor, Observer<T> observer) {
            this.mExecutor = executor;
            this.mObserver = observer;
        }

        @Override
        public void onSubscribe(Dispose dispose) {
            mExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    mObserver.onSubscribe(dispose);
                }
            });
        }

        @Override
        public void onComplete() {
            mExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    mObserver.onComplete();
                }
            });
        }

        @Override
        public void onNext(T data) {
            mExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    mObserver.onNext(data);
                }
            });
        }

        @Override
        public void onError(Exception e) {
            mExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    mObserver.onError(e);
                }
            });
        }
    }
}
