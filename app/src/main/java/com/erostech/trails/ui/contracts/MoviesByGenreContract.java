package com.erostech.trails.ui.contracts;

import com.erostech.trails.core.data.models.Movie;

import java.util.List;

/**
 * Created by erosgarciaponte on 10/05/2017.
 */

public interface MoviesByGenreContract {
    interface View {
        void showInitialPage(List<Movie> movies);
        void showNextPage(List<Movie> movies);
        void showInitialPageError(Throwable throwable);
        void showNextPageError(Throwable throwable);
        void showComplete();
    }

    interface Presenter {
        void loadInitialPage(String genreId, int page);
        void loadNextPage(String genreId, int page);
    }
}
