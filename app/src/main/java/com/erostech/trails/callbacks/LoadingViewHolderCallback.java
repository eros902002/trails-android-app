package com.erostech.trails.callbacks;

import android.support.annotation.Nullable;

/**
 * Created by erosgarciaponte on 20/04/2017.
 */

public interface LoadingViewHolderCallback {
    void showRetry(boolean show, @Nullable String errorMessage);
}
