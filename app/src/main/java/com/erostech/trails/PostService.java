package com.erostech.trails;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by erosgarciaponte on 18/04/2017.
 */

public interface PostService {
    @GET("/posts")
    Observable<List<Post>> getPostList();
}
