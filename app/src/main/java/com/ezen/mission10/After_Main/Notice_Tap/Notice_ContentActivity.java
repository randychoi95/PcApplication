package com.ezen.mission10.After_Main.Notice_Tap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.ezen.mission10.R;

public class Notice_ContentActivity extends AppCompatActivity {
    String title;
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_content);

        TextView textViewTitle = findViewById(R.id.textViewTitle);
        textViewTitle.setText(title);
        Toast.makeText(this, "title : " + title, Toast.LENGTH_SHORT).show();

        TextView text_content = findViewById(R.id.text_content);
        text_content.setText(content);
        Toast.makeText(this, "content : " + content, Toast.LENGTH_SHORT).show();

    }
}
