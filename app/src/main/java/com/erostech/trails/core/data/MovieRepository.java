package com.erostech.trails.core.data;

import com.erostech.trails.core.data.models.Movie;
import com.erostech.trails.core.data.source.DataSource;
import com.erostech.trails.core.data.source.remote.MovieRemoteDataSource;

/**
 * Created by erosgarciaponte on 26/04/2017.
 */

public class MovieRepository implements DataSource<Movie> {
    private MovieRemoteDataSource mRemoteDataSource;

    public MovieRepository() {
        mRemoteDataSource = new MovieRemoteDataSource();
    }

    @Override
    public void getAll(GetAllCallback<Movie> callback) {
        if (mRemoteDataSource != null) {
            mRemoteDataSource.getAll(callback);
        }
    }
}
