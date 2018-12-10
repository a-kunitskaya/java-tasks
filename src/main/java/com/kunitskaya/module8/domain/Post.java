package com.kunitskaya.module8.domain;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

public class Post {
    private int id;
    private int userId;
    private String text;
    private Timestamp timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
