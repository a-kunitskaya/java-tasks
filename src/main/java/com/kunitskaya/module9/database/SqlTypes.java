package com.kunitskaya.module9.database;

import java.util.Arrays;

public enum SqlTypes {
    VARCHAR(12, "String"),
    INTEGER(4, "int");

    private int code;
    private String javaType;

    SqlTypes(int code, String javaType) {
        this.code = code;
        this.javaType = javaType;
    }

    public int getCode() {
        return code;
    }

    public String getJavaType() {
        return javaType;
    }

    public SqlTypes getTypeByJavaType(String javaType) {
        return Arrays.stream(values())
                     .filter(t -> t.getJavaType().equals(javaType))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("No sql type is found for java type: " + javaType));
    }
}
