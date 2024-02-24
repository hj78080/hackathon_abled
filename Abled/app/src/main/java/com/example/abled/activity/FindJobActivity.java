package com.example.abled.activity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abled.R;
import com.example.abled.util.ApiRequest;
import com.example.abled.util.Job;
import com.example.abled.util.MenuBarHelper;
import com.example.abled.util.JobListAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class FindJobActivity extends AppCompatActivity {

    private int year;
    private String type;
    private String state;
    private String region;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findjob);

        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        year = prefs.getInt("year", 2000);
        type = prefs.getString("type", "지체장애");
        state = prefs.getString("state", "중증");
        region = prefs.getString("region", "서울");

        MenuBarHelper menuBarHelper = new MenuBarHelper(this);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            menuBarHelper.handleItemSelected(item);
            return true;
        });

        // 실시간 구인 API에 모든 구인 데이터를 요청함
        String apiKey = "apikey";
        String endpoint = "https://apis.data.go.kr/B552583/job/job_list_env";
        ApiRequest ar = new ApiRequest(apiKey, endpoint);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        new Thread(() -> {
            String data = ar.requestData(9999);
            List<Job> jobs = Job.parseXml(data);
            List<Job> filteredJobs = new ArrayList<>();

            for (Job job : jobs) {
                String jobRegion = job.getCompAddr();
                if(jobRegion.contains(region)) filteredJobs.add(job);
            }
            runOnUiThread(() -> {
                JobListAdapter adapter = new JobListAdapter(filteredJobs); // 데이터 리스트가 필요
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }
}
