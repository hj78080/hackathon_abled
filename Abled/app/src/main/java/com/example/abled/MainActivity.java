package com.example.abled;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    /*
        year = prefs.getInt("year", 2000);
        type = prefs.getString("type", "지체장애");
        state = prefs.getString("state", "중증");
        region = prefs.getString("region", "서울");
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SharedPreferences를 사용하여 앱이 처음 실행되었는지 확인
        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        boolean isFirstRun = prefs.getBoolean("isFirstRun", true);
        if (isFirstRun) {
            // 앱이 처음 실행되었을 때 소개 화면으로 넘어감
            startActivity(new Intent(this, OnboardingActivity.class));
            this.finish();
        }


        // 버튼
        Button buttonFindJob = findViewById(R.id.button_findJob);
        Button buttonCommunity = findViewById(R.id.button_community);
        Button buttonAutoML = findViewById(R.id.button_automl);
        buttonFindJob.setOnClickListener(this);
        buttonCommunity.setOnClickListener(this);
        buttonAutoML.setOnClickListener(this);


        // 하단 메뉴 바
        MenuBarHelper menuBarHelper = new MenuBarHelper(this);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                menuBarHelper.handleItemSelected(item);
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        int itemId = view.getId();
        System.out.println(itemId);

        if(itemId == R.id.button_findJob) {
            System.out.println("asdads");
        }

        if (itemId == R.id.button_community) {
            Intent intent = new Intent(MainActivity.this, CommunityActivity.class);
            startActivity(intent);
        }

        if (itemId == R.id.button_automl){

        }
    }
}