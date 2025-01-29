package com.nure_ua_tarasov.labtask45;
package com.example.noteapp;

public class Note {
    private int id;
    private String title;
    private String description;
    private String importance;
    private String dateTime;
    private String imagePath;

    public Note() {
    }

    public Note(int id, String title, String description, String importance, String dateTime, String imagePath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.importance = importance;
        this.dateTime = dateTime;
        this.imagePath = imagePath;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImportance() { return importance; }
    public void setImportance(String importance) { this.importance = importance; }
    public String getDateTime() { return dateTime; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}
