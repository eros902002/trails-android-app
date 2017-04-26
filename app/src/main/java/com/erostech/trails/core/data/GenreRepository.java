package com.erostech.trails.core.data;

import com.erostech.trails.core.data.models.Genre;
import com.erostech.trails.core.data.source.DataSource;
import com.erostech.trails.core.data.source.remote.GenreRemoteDataSource;

/**
 * Created by erosgarciaponte on 26/04/2017.
 */

public class GenreRepository implements DataSource<Genre> {
    private GenreRemoteDataSource mRemoteDataSource;

    public GenreRepository() {
        mRemoteDataSource = new GenreRemoteDataSource();
    }

    @Override
    public void getAll(GetAllCallback<Genre> callback) {
        if (mRemoteDataSource != null) {
            mRemoteDataSource.getAll(callback);
        }
    }
}
