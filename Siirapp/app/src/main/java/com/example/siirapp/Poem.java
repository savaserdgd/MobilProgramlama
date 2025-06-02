package com.example.siirapp;

public class Poem {
    private String title;
    private String content;
    private String author;
    private long timestamp;

    // Firebase için boş constructor
    public Poem() {
    }

    public Poem(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.timestamp = System.currentTimeMillis();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}