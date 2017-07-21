package ru.relex.rxretrofitbus;

import android.app.Application;

import ru.relex.rxretrofitbus.rxbus.RxEventBus;

/**
 * Created by Turkin A. on 21.07.2017.
 */

public class RxRetrofitBusApplication extends Application {

    public static final String TAG = "RxRetrofitBusApplication";

    private RxEventBus mBus;

    @Override
    public void onCreate() {
        super.onCreate();

        mBus = new RxEventBus();
    }

    public RxEventBus getBus() {
        return mBus;
    }
}
