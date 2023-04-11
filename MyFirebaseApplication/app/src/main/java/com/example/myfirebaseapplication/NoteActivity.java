package com.example.myfirebaseapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirebaseapplication.service.FirebaseStorageService;

public class NoteActivity extends AppCompatActivity {

    private TextView textView;

    // TESTING
    private static final int REQUEST_IMAGE_PICKER = 1;
    private ImageView imageView;
    private Uri selectedImageUri; // class-level variable to store the URI of the selected image
    private String docId;

    // Create an instance of FirebaseStorageService
    private FirebaseStorageService firebaseStorageService = new FirebaseStorageService();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String txt = intent.getStringExtra("text");
        docId = intent.getStringExtra("docId");

        System.out.println(txt);
        textView.setText(txt);

        // String imageURL = firebaseService.getImageFromNote(docId);
        firebaseStorageService.getImage(this, docId, imageView);

        // Launch the camera intent when the camera button is clicked
        Button cameraButton = findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(view -> {
            // cameraLauncher.launch(null);
        });
        // Launch the gallery intent when the gallery button is clicked
        Button button = findViewById(R.id.galleryButton);
        button.setOnClickListener(view -> {
            imagePickerLauncher.launch("image/*");
        });
    }

    // Create an ActivityResultLauncher to handle the result
    ActivityResultLauncher<String> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            result -> {
                // Handle the result here
                if (result != null) {
                    // An image was picked, do something with it
                    imageView.setImageURI(result);
                    selectedImageUri = result;
                }
            }
    );

    public void savePressed(View view) {
        if (selectedImageUri != null) {
            // Upload the image file to Firebase Storage and add the url to note
            firebaseStorageService.uploadImage(selectedImageUri, docId);
        }
        finish();
    }

    public void goBackPressed(View view) {
        finish();
    }
}