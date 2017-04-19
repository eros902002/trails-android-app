package com.erostech.trails.ui.contracts;

import com.erostech.trails.core.data.models.Country;
import com.erostech.trails.core.data.models.Post;

import java.util.List;

/**
 * Created by erosgarciaponte on 18/04/2017.
 */

public interface MainScreenContract {
    interface View {
        void showCountries(List<Country> posts);
        void showError(String message);
        void showComplete();
    }

    interface Presenter {
        void loadCountries();
    }
}
