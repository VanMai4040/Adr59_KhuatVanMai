package com.example.bt5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button btnBack, btnNext;
    TextView txtNumber;
    int currentImage = 0;
    int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        txtNumber = findViewById(R.id.txtNumber);
        updateImage();

        btnNext.setOnClickListener(v -> {
            if (currentImage < images.length - 1) {currentImage++;updateImage();
            }
        });

        btnBack.setOnClickListener(v -> {
            if (currentImage > 0) {currentImage--;updateImage();
            }
        });
    }
    private void updateImage() {
        imageView.setImageResource(images[currentImage]);
        txtNumber.setText((currentImage + 1) + " / " + images.length);

        if (currentImage == 0) {
            btnBack.setVisibility(View.INVISIBLE);
        } else {
            btnBack.setVisibility(View.VISIBLE);
        }

        if (currentImage == images.length - 1) {
            btnNext.setVisibility(View.INVISIBLE);
        } else {
            btnNext.setVisibility(View.VISIBLE);
        }
    }
}