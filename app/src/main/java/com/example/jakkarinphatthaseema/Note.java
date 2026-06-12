package com.example.jakkarinphatthaseema;

import java.util.Date;

public abstract class Note {
    public String title;
    public String content;
    public Date createdDate;
    public User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public abstract String getSummary();

    public String display() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title).append("\n");
        sb.append("Summary: ").append(getSummary()).append("\n");
        if (user != null) {
            sb.append("By: ").append(user.name).append(" (").append(user.sex).append(", ").append(user.age).append(")\n");
        }
        sb.append("Date: ").append(createdDate != null ? createdDate.toString() : "N/A").append("\n");
        return sb.toString();
    }
}
