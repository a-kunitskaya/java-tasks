package com.kunitskaya.module8.domain;

import java.sql.Timestamp;

public class Like {
    private int postId;
    private int userId;
    private Timestamp timestamp;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Like{" +
                "postId=" + postId +
                ", userId=" + userId +
                ", timestamp=" + timestamp +
                '}';
    }
}
