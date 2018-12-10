package com.kunitskaya.module8.domain;

import java.sql.Timestamp;

public class Friendship {
    private int userId1;
    private int userId2;
    private Timestamp timestamp;

    public int getUserId1() {
        return userId1;
    }

    public void setUserId1(int userId1) {
        this.userId1 = userId1;
    }

    public int getUserId2() {
        return userId2;
    }

    public void setUserId2(int userId2) {
        this.userId2 = userId2;
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
                ", userId2=" + userId2 +
                ", timestamp=" + timestamp +
                '}';
    }
}
