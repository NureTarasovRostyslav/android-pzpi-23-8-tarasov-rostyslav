package com.nure_ua_tarasov.labtask45;

import java.util.Date;

public class Note {
    private String title;
    private String description;
    private Importance importance;
    private Date timestamp;
    private String imageUri;
    private long id; // For uniquely identifying notes

    public enum Importance {
        LOW,
        MEDIUM,
        HIGH
    }

    public Note(String title, String description, Importance importance, String imageUri) {
        this.title = title;
        this.description = description;
        this.importance = importance;
        this.imageUri = imageUri;
        this.timestamp = new Date();
        this.id = System.currentTimeMillis(); // Simple way to generate unique IDs
    }

    // Getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Importance getImportance() { return importance; }
    public void setImportance(Importance importance) { this.importance = importance; }
    public Date getTimestamp() { return timestamp; }
    public String getImageUri() { return imageUri; }
    public void setImageUri(String imageUri) { this.imageUri = imageUri; }
    public long getId() { return id; }
}