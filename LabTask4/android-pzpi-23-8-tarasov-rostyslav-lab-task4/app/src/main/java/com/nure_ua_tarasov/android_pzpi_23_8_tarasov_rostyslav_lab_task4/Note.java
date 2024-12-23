package com.nure_ua_tarasov.android_pzpi_23_8_tarasov_rostyslav_lab_task4;

public class Note {
    private long ID;
    private String title;
    private String content;
    private String date;
    private String time;
    private String imageURI;
    private Integer importance;

    Note() {
    }

    Note(String title, String content, String date, String time, String imageURI, Integer importance) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
        this.imageURI = imageURI;
        this.importance = importance;

    }
    Note(long id, String title, String content, String date, String time, String imageURI, Integer importance){
        this.ID = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
        this.imageURI = imageURI;
        this.importance = importance;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }
}
