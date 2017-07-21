package ru.relex.rxretrofitbus;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.relex.rxretrofitbus.data.retrofit.UniversityRetrofitService;
import ru.relex.rxretrofitbus.rxbus.RxEventBus;

/**
 * Created by Turkin A. on 21.07.2017.
 */

public class RxRetrofitBusApplication extends Application {

    public static final String TAG = RxRetrofitBusApplication.class.getSimpleName();
    public static final String URL_BASE = "http://universities.hipolabs.com/";

    private RxEventBus mBus;

    private Retrofit mRetrofit;
    private UniversityRetrofitService mNetworkService;

    @Override
    public void onCreate() {
        super.onCreate();

        mBus = new RxEventBus();
        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL_BASE)
                .build();

        mNetworkService = mRetrofit.create(UniversityRetrofitService.class);
    }

    public RxEventBus getBus() {
        return mBus;
    }

    public UniversityRetrofitService getRetrofitService() {
        return mNetworkService;
    }
}
