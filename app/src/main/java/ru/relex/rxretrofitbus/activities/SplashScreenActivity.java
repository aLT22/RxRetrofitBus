package ru.relex.rxretrofitbus.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.relex.rxretrofitbus.R;
import ru.relex.rxretrofitbus.RxRetrofitBusApplication;
import ru.relex.rxretrofitbus.rxbus.RxEvents;

public class SplashScreenActivity extends AppCompatActivity {

    public static final String TAG = SplashScreenActivity.class.getSimpleName();

    private CompositeDisposable mDisposable = new CompositeDisposable();

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mUnbinder = ButterKnife.bind(this);

        mDisposable.add(((RxRetrofitBusApplication) getApplication())
                .getBus()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object -> {
                    if (object instanceof RxEvents.CheckingSharedPreferencesEvent) {
                        ((RxRetrofitBusApplication) getApplication())
                                .getBus()
                                .sendEvent(new RxEvents.LoadUniversitiesEvent());
                        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mUnbinder.unbind();
        mDisposable.clear();
    }
}
