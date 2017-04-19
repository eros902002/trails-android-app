package com.erostech.trails.ui.presenters;

import com.erostech.trails.core.TrailsService;
import com.erostech.trails.core.data.models.Country;
import com.erostech.trails.core.data.models.Post;
import com.erostech.trails.core.PostService;
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
    public void loadCountries() {
        mRetrofit.create(TrailsService.class)
                .getCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Country>>() {
                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        mView.showError(throwable.getMessage());
                    }

                    @Override
                    public void onNext(List<Country> countries) {
                        mView.showCountries(countries);
                    }
                });
    }
}
