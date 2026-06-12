package com.example.jakkarinphatthaseema;

import java.util.Date;

public class TextNote extends Note {

    public TextNote(String title, Date createdDate, String content) {
        super();
        this.title = title;
        this.createdDate = createdDate;
        this.content = content;
    }

    @Override
    public String getSummary() {
        return this.content != null ? this.content : "";
    }
}
