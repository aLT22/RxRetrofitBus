package ru.relex.rxretrofitbus.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.relex.rxretrofitbus.R;
import ru.relex.rxretrofitbus.RxRetrofitBusApplication;
import ru.relex.rxretrofitbus.data.model.University;
import ru.relex.rxretrofitbus.rxbus.RxEvents;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private Unbinder mUnbinder;

    @BindView(R.id.lv)
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUnbinder = ButterKnife.bind(this);

        ArrayAdapter<String> universityArrayAdapter
                = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1);
        mListView.setAdapter(universityArrayAdapter);

        Observable<List<University>> universitiesObservable = ((RxRetrofitBusApplication) getApplication())
                .getRetrofitService().getUniversities("cal");

        /*universitiesObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(universities -> {
                    for (University university :
                            universities) {
                        universityArrayAdapter.add(university.getName());
                    }
                });*/

        mDisposable.add(((RxRetrofitBusApplication) getApplication())
                .getBus()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object -> {
                    if (object instanceof RxEvents.ShowEnterCityDialogEvent) {

                    } else if (object instanceof RxEvents.LoadUniversitiesEvent) {
                        Observable<List<University>> universities = ((RxRetrofitBusApplication) getApplication()).getRetrofitService().getUniversities("Voronezh");

                        universities.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(universts -> {
                                    universityArrayAdapter.add(universts.get(0).getName());
                                });
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mDisposable.clear();
        mUnbinder.unbind();
    }
}
