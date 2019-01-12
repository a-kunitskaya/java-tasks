package com.kunitskaya.module9.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public abstract class HighloadConfiguration {
    @JsonProperty("N_tables_count")
    private int nTables;

    @JsonProperty("K_columns_count")
    private int kColumnsCount;

    @JsonProperty("Z_types")
    private List<String> types;

    @JsonProperty("L_connections_count")
    private int lConnectionsCount;

    @JsonProperty("m_rows_count")
    private int mRowsCount;

    public int getnTables() {
        return nTables;
    }

    public void setnTables(int nTables) {
        this.nTables = nTables;
    }

    public int getkColumnsCount() {
        return kColumnsCount;
    }

    public void setkColumnsCount(int kColumnsCount) {
        this.kColumnsCount = kColumnsCount;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public int getlConnectionsCount() {
        return lConnectionsCount;
    }

    public void setlConnectionsCount(int lConnectionsCount) {
        this.lConnectionsCount = lConnectionsCount;
    }

    public int getmRowsCount() {
        return mRowsCount;
    }

    public void setmRowsCount(int mRowsCount) {
        this.mRowsCount = mRowsCount;
    }

    @Override
    public String toString() {
        return "HighloadConfiguration{" +
                "nTables=" + nTables +
                ", kColumnsCount=" + kColumnsCount +
                ", types=" + types +
                ", lConnectionsCount=" + lConnectionsCount +
                ", mRowsCount=" + mRowsCount +
                '}';
    }
}