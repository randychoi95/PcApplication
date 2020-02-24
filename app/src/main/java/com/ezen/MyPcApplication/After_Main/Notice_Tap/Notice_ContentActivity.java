package com.ezen.MyPcApplication.After_Main.Notice_Tap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.ezen.MyPcApplication.R;

public class Notice_ContentActivity extends AppCompatActivity {
    TextView textViewTitle;
    TextView text_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_content);

        // 툴바에 뒤로가기 백버튼 생성
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // DB 정보 받아서 리스트뷰에 출력
        Intent intent = getIntent();
        String notice_title = intent.getExtras().getString("title");
        String notice_content = intent.getExtras().getString("content");

        textViewTitle = findViewById(R.id.notice_Title);
        textViewTitle.setText(notice_title);

        text_content = findViewById(R.id.notice_content);
        text_content.setText(notice_content);


    }

    // 뒤로가기 버튼 누를 시에 이벤트 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
