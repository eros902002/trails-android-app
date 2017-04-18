package com.erostech.trails.ui;

import com.erostech.trails.Post;

import java.util.List;

/**
 * Created by erosgarciaponte on 18/04/2017.
 */

public interface MainScreenContract {
    interface View {
        void showPosts(List<Post> posts);
        void showError(String message);
        void showComplete();
    }

    interface Presenter {
        void loadPost();
    }
}
