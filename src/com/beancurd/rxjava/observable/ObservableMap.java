package com.beancurd.rxjava.observable;

import com.beancurd.rxjava.Dispose;
import com.beancurd.rxjava.Observable;
import com.beancurd.rxjava.Observer;
import com.beancurd.rxjava.dispose.DisposeJust;

public class ObservableMap<T,R> extends Observable<R> {

    private Observable<T> mUp;

    private Mapper<T, R> mMapper;


    public ObservableMap(Observable<T> up, Mapper<T, R> mapper) {
        this.mUp = up;
        this.mMapper = mapper;
    }


    @Override
    protected void subscribeActual(Observer<R> observer) {
        mUp.observe(new ObserverMapper<T,R>(mMapper, observer));
    }


    public static class ObserverMapper<T,R> implements Observer<T> {

        private Mapper<T, R> mMapper;

        private Observer<R> mObserver;

        public ObserverMapper(Mapper<T, R> mapper, Observer<R> observer) {
            this.mMapper = mapper;
            this.mObserver = observer;
        }

        @Override
        public void onSubscribe(Dispose dispose) {
            mObserver.onSubscribe(dispose);
        }

        @Override
        public void onComplete() {
            mObserver.onComplete();
        }

        @Override
        public void onNext(T data) {
            mObserver.onNext(mMapper.map(data));
        }

        @Override
        public void onError(Exception e) {
            mObserver.onError(e);
        }
    }

    public interface Mapper<T,R> {
        R map(T rawData);
    }
}
