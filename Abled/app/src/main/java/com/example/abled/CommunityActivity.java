package com.example.abled;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class CommunityActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);


        Button btCommunityAsk = findViewById(R.id.button_community_ask);
        Button btCommunityInfo = findViewById(R.id.button_community_info);
        Button btCommunityJob = findViewById(R.id.button_community_job);
        btCommunityAsk.setOnClickListener(this);
        btCommunityInfo.setOnClickListener(this);
        btCommunityJob.setOnClickListener(this);

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

        if(itemId == R.id.button_community_ask) {
            Toast.makeText(CommunityActivity.this, "미구현 입니다.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(CommunityActivity.this, CommunityAskActivity.class);
            startActivity(intent);
        }

        if (itemId == R.id.button_community_info) {
            Toast.makeText(CommunityActivity.this, "미구현 입니다.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(CommunityActivity.this, CommunityAskActivity.class);
            startActivity(intent);
        }

        if (itemId == R.id.button_community_job){
            Toast.makeText(CommunityActivity.this, "미구현 입니다.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(CommunityActivity.this, CommunityAskActivity.class);
            startActivity(intent);
        }
    }
}
