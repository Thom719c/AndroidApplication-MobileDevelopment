package com.example.myfirebaseapplication.service;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class FirebaseStorageService {
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference();
    private FirebaseService firebaseService = new FirebaseService();

    public void getImage(Context context, String docId, ImageView imageView) {
        firebaseService.getImageFromNote(docId, imageURL -> {
            // Do something with the imageURL
            System.out.println("Image URL: " + imageURL);
            // This could work but need some modification before it works but i used Glide instead.
            // imageView.setImageURI(Uri.parse(imageURL));
            if (imageURL != null) {
                Glide.with(context).load(imageURL).into(imageView);
            }
        }, e -> {
            // Handle the failure
            System.out.println("Error retrieving image URL: " + e.getMessage());
        });
    }

    public void uploadImage(Uri imageUri, String docId) {
        // Create a reference to the file in Firebase Storage
        StorageReference fileRef = storageRef.child("images/" + UUID.randomUUID().toString());

        // Upload the file to Firebase Storage
        fileRef.putFile(imageUri)
            .addOnSuccessListener(taskSnapshot -> {
                // File uploaded successfully
                // Get a download URL for the file
                fileRef.getDownloadUrl()
                    .addOnSuccessListener(uri -> {
                        //fileRef.getName();
                        // Get the download URL
                        String downloadUrl = uri.toString();
                        // Do something with the download URL, like save it to Firebase Database
                        // or display the image using an ImageView
                        // firebaseService.addImageToNote(docId, downloadUrl);
                        firebaseService.addImageToNote(docId, fileRef.getName(), downloadUrl);
                    })
                    .addOnFailureListener(e -> {
                        // Failed to get download URL
                        e.printStackTrace();
                    });
            })
            .addOnFailureListener(e -> {
                // File upload failed
                e.printStackTrace();
            });
    }
}
