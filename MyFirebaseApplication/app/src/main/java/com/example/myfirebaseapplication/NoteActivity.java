package com.example.myfirebaseapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myfirebaseapplication.service.FirebaseStorageService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class NoteActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editText;

    // TESTING
    private static final int REQUEST_IMAGE_PICKER = 1;
    private static final int CAMERA_REQUEST = 1888;
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

        editTextTitle = findViewById(R.id.editTextTitle);
        editText = findViewById(R.id.editText);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        docId = intent.getStringExtra("docId");
        String txt = intent.getStringExtra("text");
        String title = intent.getStringExtra("title");
        String imageName = intent.getStringExtra("imageName");
        String imageUrl = intent.getStringExtra("imageUrl");

        editTextTitle.setText(title);
        editText.setText(txt);

        //firebaseStorageService.getImage(this, docId, imageView);
        if (!imageUrl.isEmpty()) {
            Glide.with(this).load(imageUrl).into(imageView);
        }

        // Launch the camera intent when the camera button is clicked
        Button cameraButton = findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(view -> {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            cameraLauncher.launch(cameraIntent);
        });

        // Launch the gallery intent when the gallery button is clicked
        Button button = findViewById(R.id.galleryButton);
        button.setOnClickListener(view -> {
            imagePickerLauncher.launch("image/*");
        });
    }


    ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Bitmap photo = (Bitmap) result.getData().getExtras().get("data");
                    imageView.setImageBitmap(photo);

                    // Save the Bitmap to a file
                    File file = new File(getExternalFilesDir(null), "image.jpg");
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        photo.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Get the Uri of the file
                    Uri uri = Uri.fromFile(file);
                    selectedImageUri = uri;
                }
            }
    );

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
        String title = editTextTitle.getText().toString().trim();
        String text = editText.getText().toString().trim();
        if (!text.isEmpty() || selectedImageUri != null) {
            // Upload the image file to Firebase Storage and add the url to note
            firebaseStorageService.updateNote(title, text, selectedImageUri, docId);
        }
        finish();
    }

    public void goBackPressed(View view) {
        finish();
    }
}