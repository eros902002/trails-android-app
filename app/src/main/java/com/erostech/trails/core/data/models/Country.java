package com.erostech.trails.core.data.models;

/**
 * Created by erosgarciaponte on 18/04/2017.
 */

public class Country {
    private final String name;
    private final String code;
    private final String description;
    private final String flag;

    public Country(String name, String code, String description, String flag) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getFlag() {
        return flag;
    }
}
