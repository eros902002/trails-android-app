package com.erostech.trails.ui.contracts;

import com.erostech.trails.core.data.models.Genre;

import java.util.List;

/**
 * Created by erosgarciaponte on 27/04/2017.
 */

public interface GenreListContract {
    interface View {
        void showGenres(List<Genre> genres);
        void showError(Throwable throwable);
        void showComplete();
    }

    interface Presenter {
        void loadGenres();
    }
}
