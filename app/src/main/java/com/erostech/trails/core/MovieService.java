package com.erostech.trails.core;

import com.erostech.trails.core.data.models.TopRatedMovies;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by erosgarciaponte on 19/4/17.
 */

public interface MovieService {
    @GET("top_rated")
    Observable<TopRatedMovies> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int pageIndex);
}
