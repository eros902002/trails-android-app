package com.erostech.trails.core.data.source;

import java.util.List;

/**
 * Created by erosgarciaponte on 26/04/2017.
 */

public interface DataSource<T> {
    void getAll(GetAllCallback<T> callback);

    interface GetAllCallback<T> {
        void onSuccess(List<T> list);
        void onError(Throwable throwable);
    }
}
