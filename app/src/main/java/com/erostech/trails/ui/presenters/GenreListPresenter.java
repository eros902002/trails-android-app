package com.erostech.trails.ui.presenters;

import com.erostech.trails.config.Config;
import com.erostech.trails.core.MovieService;
import com.erostech.trails.core.data.models.GenreList;
import com.erostech.trails.ui.contracts.GenreListContract;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by erosgarciaponte on 27/04/2017.
 */

public class GenreListPresenter implements GenreListContract.Presenter {
    private Retrofit mRetrofit;
    private GenreListContract.View mView;

    @Inject
    public GenreListPresenter(Retrofit retrofit, GenreListContract.View view) {
        this.mRetrofit = retrofit;
        this.mView = view;
    }


    @Override
    public void loadGenres() {
        fetchGenres(new Observer<GenreList>() {
            @Override
            public void onCompleted() {
                mView.showComplete();
            }

            @Override
            public void onError(Throwable throwable) {
                mView.showError(throwable);
            }

            @Override
            public void onNext(GenreList genreList) {
                mView.showGenres(genreList.getGenres());
            }
        });
    }

    private void fetchGenres(Observer<GenreList> observer) {
        mRetrofit.create(MovieService.class)
                .getMovieGenres(Config.MOVIES_API_KEY, Config.MOVIES_DEFAULT_LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
