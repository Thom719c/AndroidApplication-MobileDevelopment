package com.example.myfirebaseapplication.model;

public class Note {
    private String documentId;
    private String title;
    private String text;

    private String imageName;
    private String imageUrl;

    public Note(String documentId, String text) {
        this.documentId = documentId;
        this.text = text;
    }

    public Note(String documentId, String title, String text, String imageName, String imageUrl) {
        this.documentId = documentId;
        this.title = title;
        this.text = text;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
    }

    public String getDocumentId() {
        return documentId;
    }
    public String getTitle() {
        return title;
    }
    public String getText() {
        return text;
    }

    public String getImageName() {
        return imageName;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return text;
        //return "documentId: " + documentId + "text: " + text;
    }
}

