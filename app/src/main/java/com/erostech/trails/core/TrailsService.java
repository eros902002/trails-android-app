package com.erostech.trails.core;

import com.erostech.trails.core.data.models.Country;
import com.erostech.trails.core.data.models.Province;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by erosgarciaponte on 19/04/2017.
 */

public interface TrailsService {
    @GET("country")
    Observable<List<Country>> getCountries();

    @GET("country/{code}")
    Observable<List<Province>> getProvinces(@Path("code") String countryCode);
}
