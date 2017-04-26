package com.erostech.trails.core.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by erosgarciaponte on 26/04/2017.
 */

public class GenreList {
    @SerializedName("genres")
    @Expose
    private List<Genre> genres;

    public GenreList() {
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
