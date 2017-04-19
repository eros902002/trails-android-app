package com.erostech.trails.core.data.models;

/**
 * Created by erosgarciaponte on 18/04/2017.
 */

public class Province {
    private final String name;
    private final String description;
    private final String flag;

    public Province(String name, String description, String flag) {
        this.name = name;
        this.description = description;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFlag() {
        return flag;
    }
}
