package com.erostech.trails.ui;

import com.erostech.trails.Post;
import com.erostech.trails.PostService;

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
    public void loadPost() {
        mRetrofit.create(PostService.class)
                .getPostList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        mView.showError(throwable.getMessage());
                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        mView.showPosts(posts);
                    }
                });
    }
}
