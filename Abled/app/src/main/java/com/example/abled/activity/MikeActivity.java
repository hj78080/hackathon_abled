package com.example.abled.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.abled.util.HttpRequest;
import com.example.abled.util.MenuBarHelper;
import com.example.abled.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MikeActivity extends AppCompatActivity {

    Intent intent;
    TextView textView;
    String text;
    final int PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mike);

        MenuBarHelper menuBarHelper = new MenuBarHelper(this);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            menuBarHelper.handleItemSelected(item);
            return true;
        });

        // 권한 체크
        if ( Build.VERSION.SDK_INT >= 23 ){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET, Manifest.permission.RECORD_AUDIO}, PERMISSION);
        }

        textView = findViewById(R.id.stt_result);
        Button btStt = findViewById(R.id.button_stt);

        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ko-KR");

        btStt.setOnClickListener(v -> {
            SpeechRecognizer mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
            mRecognizer.setRecognitionListener(listener);
            mRecognizer.startListening(intent);
        });
    }


    // 음성 인식 종료시 호출, 해당 페이지로 이동함
    private void movePage(String page){
        Class cls = null;
        String dialog = null;

        switch (page){
            case "info":
                cls = SetInfoActivity.class;
                dialog = "정보 수정";
                break;
            case "joblist":
                cls = FindJobActivity.class;
                dialog = "일자리 찾기";
                break;
            case "automl":
                cls = AutoMLActivity.class;
                dialog = "유형 별 고용현황";
                break;
            case "commu":
                cls = CommunityActivity.class;
                dialog = "커뮤니티";
                break;
            case "home":
                cls = MainActivity.class;
                dialog = "처음";
                break;
            default:
                Toast.makeText(getApplicationContext(),"다시 말씀해 주세요.",Toast.LENGTH_SHORT).show();
                return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage(dialog + " 페이지로 이동하시겠습니까?");

        Class finalCls = cls;
        builder.setPositiveButton("예", (dialogInterface, i) -> {
            Intent pageIntent = new Intent(MikeActivity.this, finalCls);
            startActivity(pageIntent);
        });
        builder.setNegativeButton("아니오", null);
        builder.show();
    }


    // 음성 인식 클래스
    private RecognitionListener listener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle params) {
            Toast.makeText(getApplicationContext(),"음성인식을 시작합니다.",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onBeginningOfSpeech() {}

        @Override
        public void onRmsChanged(float rmsdB) {}

        @Override
        public void onBufferReceived(byte[] buffer) {}

        @Override
        public void onEndOfSpeech() {}

        @Override
        public void onError(int error) {
            Toast.makeText(getApplicationContext(), "다시 말씀해 주세요.", Toast.LENGTH_SHORT).show();
        }

        //음성 인식 결과를 서버에 전송 하여, 페이지 추천을 응답 받음
        @Override
        public void onResults(Bundle results) {
            // 말을 하면 ArrayList에 단어를 넣고 textView에 단어를 이어준다.
            ArrayList<String> matches =
                    results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            text = "";
            for(String s : matches) text += s;

            textView.setText(text);
            new Thread(() -> {
                String response = HttpRequest.sendPostRequest(text);

                // 추천 페이지가 없을 경우
                if (response.equals("none")) {
                    Toast.makeText(getApplicationContext(), "관련 페이지가 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                runOnUiThread(() -> {
                    movePage(response);
                });
            }).start();
        }

        @Override
        public void onPartialResults(Bundle partialResults) {}

        @Override
        public void onEvent(int eventType, Bundle params) {}
    };
}
