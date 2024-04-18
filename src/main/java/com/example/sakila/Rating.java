package com.example.sakila;

public enum Rating {
    G,
    PG,
    PG_13,
    R,
    NC_17;

    public String toStringWithDashes() {
        return this.name().replace("_", "-");
    }

    public static Rating fromString(String ratingString) {
        return Rating.valueOf(ratingString.replace("-", "_"));
    }
}
