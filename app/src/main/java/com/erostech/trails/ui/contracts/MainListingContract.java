package com.erostech.trails.ui.contracts;

import com.erostech.trails.core.data.models.Movie;

import java.util.List;

/**
 * Created by erosgarciaponte on 18/04/2017.
 */

public interface MainListingContract {
    interface View {
        void showInitialPage(List<Movie> movies);
        void showNextPage(List<Movie> movies);
        void showInitialPageError(Throwable throwable);
        void showNextPageError(Throwable throwable);
        void showComplete();
    }

    interface Presenter {
        void loadInitialPage(int page);
        void loadNextPage(int page);
    }
}
