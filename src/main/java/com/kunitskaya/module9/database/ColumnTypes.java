package com.kunitskaya.module9.database;

public enum ColumnTypes {
    VARCHAR(12),
    INTEGER(4);

    private int code;

    ColumnTypes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
