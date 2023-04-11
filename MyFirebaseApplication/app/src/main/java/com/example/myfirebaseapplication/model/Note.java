package com.example.myfirebaseapplication.model;

public class Note {
    private String documentId;
    private String text;

    private String imageName;
    private String imageUrl;

    public Note(String documentId, String text) {
        this.documentId = documentId;
        this.text = text;
    }

    public Note(String documentId, String text, String imageName, String imageUrl) {
        this.documentId = documentId;
        this.text = text;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
    }

    public String getDocumentId() {
        return documentId;
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
        return "Note text\n" + text;
        //return "documentId: " + documentId + "text: " + text;
    }
}

