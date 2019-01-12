package com.kunitskaya.module9.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class TwoDimensionalArray extends HighloadConfiguration {
    @JsonProperty("M_array")
    private int[][] array;

    public int[][] getArray() {
        return array;
    }

    public void setArray(int[][] array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return "TwoDimensionalArray{" +
                "array=" + Arrays.toString(array) +
                "} " + super.toString();
    }
}
