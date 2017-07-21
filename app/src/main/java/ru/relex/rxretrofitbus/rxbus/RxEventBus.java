package ru.relex.rxretrofitbus.rxbus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Turkin A. on 21.07.2017.
 */

public class RxEventBus {

    private PublishSubject<Object> mBus = PublishSubject.create();

    public RxEventBus() {}

    public Observable<Object> toObservable() {
        return mBus;
    }

    public void sendEvent(Object event) {
        mBus.onNext(event);
    }

}
