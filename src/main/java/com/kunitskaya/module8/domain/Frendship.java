package com.kunitskaya.module8.domain;

import java.sql.Timestamp;

public class Frendship {
    private int userId1;
    private int getUserId2;
    private Timestamp timestamp;

    public int getUserId1() {
        return userId1;
    }

    public void setUserId1(int userId1) {
        this.userId1 = userId1;
    }

    public int getGetUserId2() {
        return getUserId2;
    }

    public void setGetUserId2(int getUserId2) {
        this.getUserId2 = getUserId2;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Frendship{" +
                "userId1=" + userId1 +
                ", getUserId2=" + getUserId2 +
                ", timestamp=" + timestamp +
                '}';
    }
}
