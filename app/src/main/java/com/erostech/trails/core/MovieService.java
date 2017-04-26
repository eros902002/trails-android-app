package com.erostech.trails.core;

import com.erostech.trails.core.data.models.GenreList;
import com.erostech.trails.core.data.models.MoviesByGenre;
import com.erostech.trails.core.data.models.TopRatedMovies;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by erosgarciaponte on 19/4/17.
 */

public interface MovieService {
    @GET("movie/top_rated")
    Observable<TopRatedMovies> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int pageIndex);

    @GET("genre/movie/list")
    Observable<GenreList> getMovieGenres(
            @Query("api_key") String apiKey,
            @Query("language") String language);

    @GET("genre/tv/list")
    Observable<GenreList> getTvGenres(
            @Query("api_key") String apiKey,
            @Query("language") String language);

    @GET("genre/{id}/movies")
    Observable<MoviesByGenre> getMoviesByGenre(
            @Path("id") String genreId,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("include_adult") boolean includeAdult,
            @Query("page") int pageIndex);
}
