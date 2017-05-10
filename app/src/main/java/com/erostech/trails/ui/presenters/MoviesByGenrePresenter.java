package com.erostech.trails.ui.presenters;

import com.erostech.trails.Constants;
import com.erostech.trails.config.Config;
import com.erostech.trails.core.MovieService;
import com.erostech.trails.core.data.models.MoviesByGenre;
import com.erostech.trails.ui.contracts.MoviesByGenreContract;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by erosgarciaponte on 10/05/2017.
 */

public class MoviesByGenrePresenter implements MoviesByGenreContract.Presenter {
    private Retrofit mRetrofit;
    private MoviesByGenreContract.View mView;

    @Inject
    public MoviesByGenrePresenter(Retrofit retrofit, MoviesByGenreContract.View view) {
        this.mRetrofit = retrofit;
        this.mView = view;
    }

    @Override
    public void loadInitialPage(String genreId, int page) {
        loadMovies(genreId, page, new Observer<MoviesByGenre>() {
            @Override
            public void onCompleted() {
                mView.showComplete();
            }

            @Override
            public void onError(Throwable throwable) {
                mView.showInitialPageError(throwable);
            }

            @Override
            public void onNext(MoviesByGenre movies) {
                mView.showInitialPage(movies.getMovies());
            }
        });
    }

    @Override
    public void loadNextPage(String genreId, int page) {
        loadMovies(genreId, page, new Observer<MoviesByGenre>() {
            @Override
            public void onCompleted() {
                mView.showComplete();
            }

            @Override
            public void onError(Throwable throwable) {
                mView.showNextPageError(throwable);
            }

            @Override
            public void onNext(MoviesByGenre movies) {
                mView.showNextPage(movies.getMovies());
            }
        });
    }

    private void loadMovies(String genreId, int page, Observer<MoviesByGenre> observer) {
        mRetrofit.create(MovieService.class)
                .getMoviesByGenre(genreId, Config.MOVIES_API_KEY, Config.MOVIES_DEFAULT_LANGUAGE, Config.INCLUDE_ADULT, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
