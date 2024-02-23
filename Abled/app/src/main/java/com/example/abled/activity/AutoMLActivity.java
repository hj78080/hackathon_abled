package com.example.abled.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.abled.R;
import com.example.abled.util.HttpRequest;
import com.example.abled.util.MenuBarHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AutoMLActivity extends AppCompatActivity {

    private int year;
    private String type;
    private String state;
    private String region;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automl);

        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        year = prefs.getInt("year", 2000);
        type = prefs.getString("type", "지체장애");
        state = prefs.getString("state", "중증");
        region = prefs.getString("region", "서울");

        ImageView imageView = findViewById(R.id.image_view);
        TextView textView = findViewById(R.id.text_myInfo);
        textView.append(year+"년생, "+type +", "+state +", "+ region);

        MenuBarHelper menuBarHelper = new MenuBarHelper(this);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            menuBarHelper.handleItemSelected(item);
            return true;
        });

        Button button = findViewById(R.id.button_changeInfo);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(AutoMLActivity.this, SetInfoActivity.class);
            startActivity(intent);
        });


        new Thread(() -> {
            try {
                bitmap = HttpRequest.sendImageRequest(year, type, state,region);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            runOnUiThread(() -> {
                imageView.setImageBitmap(bitmap);
            });
        }).start();
    }
}
