package com.ezen.MyPcApplication.After_Main.Notice_Tap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.ezen.MyPcApplication.R;

public class Notice_ContentActivity extends AppCompatActivity {
    String title;
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_content);

        // 툴바에 뒤로가기 백버튼 생성
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title = "공지사항 제목";
        TextView textViewTitle = findViewById(R.id.textViewTitle);
        textViewTitle.setText(title);
        Toast.makeText(this, "title : " + title, Toast.LENGTH_SHORT).show();

        content = "공지사항 내용";
        TextView text_content = findViewById(R.id.text_content);
        text_content.setText(content);
        Toast.makeText(this, "content : " + content, Toast.LENGTH_SHORT).show();

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
