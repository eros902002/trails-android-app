package com.erostech.trails.ui.contracts;

import com.erostech.trails.core.data.models.Country;
import com.erostech.trails.core.data.models.Post;
import com.erostech.trails.core.data.models.Result;

import java.util.List;

/**
 * Created by erosgarciaponte on 18/04/2017.
 */

public interface MainScreenContract {
    interface View {
        void showInitialPage(List<Result> movies);
        void showNextPage(List<Result> movies);
        void showInitialPageError(Throwable throwable);
        void showNextPageError(Throwable throwable);
        void showComplete();
    }

    interface Presenter {
        void loadInitialPage(int page);
        void loadNextPage(int page);
    }
}
