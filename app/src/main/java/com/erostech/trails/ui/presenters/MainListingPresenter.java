package com.erostech.trails.ui.presenters;

import com.erostech.trails.config.Config;
import com.erostech.trails.core.MovieService;
import com.erostech.trails.core.data.models.TopRatedMovies;
import com.erostech.trails.ui.contracts.MainListingContract;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by erosgarciaponte on 18/04/2017.
 */

public class MainListingPresenter implements MainListingContract.Presenter {
    private Retrofit mRetrofit;
    private MainListingContract.View mView;

    @Inject
    public MainListingPresenter(Retrofit retrofit, MainListingContract.View view) {
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
                mView.showInitialPage(movies.getMovies());
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
                mView.showNextPage(movies.getMovies());
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
