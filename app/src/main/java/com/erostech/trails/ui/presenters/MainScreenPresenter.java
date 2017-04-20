package com.erostech.trails.ui.presenters;

import com.erostech.trails.config.Config;
import com.erostech.trails.core.MovieService;
import com.erostech.trails.core.TrailsService;
import com.erostech.trails.core.data.models.Country;
import com.erostech.trails.core.data.models.Post;
import com.erostech.trails.core.PostService;
import com.erostech.trails.core.data.models.TopRatedMovies;
import com.erostech.trails.ui.contracts.MainScreenContract;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by erosgarciaponte on 18/04/2017.
 */

public class MainScreenPresenter implements MainScreenContract.Presenter {
    private Retrofit mRetrofit;
    private MainScreenContract.View mView;

    @Inject
    public MainScreenPresenter(Retrofit retrofit, MainScreenContract.View view) {
        this.mRetrofit = retrofit;
        this.mView = view;
    }

    @Override
    public void loadInitialPage(int page) {
        loadMovies(page, new Observer<TopRatedMovies>() {
            @Override
            public void onCompleted() {
                mView.showComplete();
            }

            @Override
            public void onError(Throwable throwable) {
                mView.showInitialPageError(throwable);
            }

            @Override
            public void onNext(TopRatedMovies movies) {
                mView.showInitialPage(movies.getResults());
            }
        });
    }

    @Override
    public void loadNextPage(int page) {
        loadMovies(page, new Observer<TopRatedMovies>() {
            @Override
            public void onCompleted() {
                mView.showComplete();
            }

            @Override
            public void onError(Throwable throwable) {
                mView.showNextPageError(throwable);
            }

            @Override
            public void onNext(TopRatedMovies movies) {
                mView.showNextPage(movies.getResults());
            }
        });
    }

    private void loadMovies(int page, Observer<TopRatedMovies> observer) {
        mRetrofit.create(MovieService.class)
                .getTopRatedMovies(Config.MOVIES_API_KEY, Config.MOVIES_DEFAULT_LANGUAGE, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
