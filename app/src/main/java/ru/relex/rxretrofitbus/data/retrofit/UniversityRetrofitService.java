package ru.relex.rxretrofitbus.data.retrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.relex.rxretrofitbus.data.model.University;

/**
 * Created by Turkin A. on 21.07.2017.
 */

public interface UniversityRetrofitService {

    @GET("search?")
    Observable<List<University>> getUniversities(@Query("name") String name);

}
