package com.example.myfirebaseapplication.model;

public class Note {
    private String documentId;
    private String text;

    public Note(String documentId, String text) {
        this.documentId = documentId;
        this.text = text;
    }

    public String getDocumentId() {
        return documentId;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Note text\n" + text;
        //return "documentId: " + documentId + "text: " + text;
    }
}

