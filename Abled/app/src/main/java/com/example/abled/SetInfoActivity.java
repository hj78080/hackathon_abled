package com.example.abled;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SetInfoActivity extends AppCompatActivity {

    private TextView infoType;
    private TextView infoDescription;
    private ListView selectList;
    private ProgressBar progressBar;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setinfo);

        infoType = findViewById(R.id.infoType);
        infoDescription = findViewById(R.id.infoDescription);
        selectList = findViewById(R.id.selectList);
        progressBar = findViewById(R.id.progressBar);
        prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);

        change2Year();
    }

    private void change2Year(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.birth_years,
                android.R.layout.simple_list_item_1
        );
        selectList.setAdapter(adapter);
        selectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 선택된 년도 처리
                String selected = (String) parent.getItemAtPosition(position);
                Toast.makeText(SetInfoActivity.this, "Selected Year: " + selected, Toast.LENGTH_SHORT).show();

                prefs.edit().putInt("year", Integer.parseInt(selected)).apply();
                change2Type();
            }
        });
    }

    private void change2Type(){
        infoType.setText("장애 유형");
        infoDescription.setVisibility(View.GONE);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.disable_types,
                android.R.layout.simple_list_item_1
        );

        selectList.setAdapter(adapter);
        selectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 선택된 장애 유형 처리
                String selected = (String) parent.getItemAtPosition(position);
                Toast.makeText(SetInfoActivity.this, "Selected Year: " + selected, Toast.LENGTH_SHORT).show();

                prefs.edit().putString("type", selected).apply();
                change2State();
            }
        });
        progressBar.setProgress(30, true);
    }

    private void change2State(){
        infoType.setText("중증 정도");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.disable_state,
                android.R.layout.simple_list_item_1
        );

        selectList.setAdapter(adapter);
        selectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 선택된 장애 유형 처리
                String selected = (String) parent.getItemAtPosition(position);
                Toast.makeText(SetInfoActivity.this, "Selected Year: " + selected, Toast.LENGTH_SHORT).show();

                prefs.edit().putString("state", selected).apply();
                change2Region();
            }
        });
        progressBar.setProgress(55, true);
    }

    private void change2Region(){
        infoType.setText("거주 지역");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.region,
                android.R.layout.simple_list_item_1
        );

        selectList.setAdapter(adapter);
        selectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 선택된 장애 유형 처리
                String selected = (String) parent.getItemAtPosition(position);
                Toast.makeText(SetInfoActivity.this, "Selected Year: " + selected, Toast.LENGTH_SHORT).show();

                prefs.edit().putString("region", selected).apply();
                Intent intent = new Intent(SetInfoActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // 설정을 마친 경우, 다음 부터 Onboarding 실행 안함.
                SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                prefs.edit().putBoolean("isFirstRun", false).apply();
                startActivity(intent);
                finish(); // 현재 액티비티 종료
            }
        });
        progressBar.setProgress(80, true);
    }

}
