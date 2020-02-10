package com.ezen.mission10.First_View;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;

import com.ezen.mission10.R;
import com.google.android.material.textfield.TextInputLayout;

public class JoinActivity extends AppCompatActivity {

    TextInputLayout pw_input_layout;
    TextInputLayout repw_input_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        // PW TextInputLayout
        pw_input_layout = findViewById(R.id.pw_input_layout);
        repw_input_layout = findViewById(R.id.repw_input_layout);
        // 비밀번호 보이기/안보이기
        pw_input_layout.setPasswordVisibilityToggleEnabled(true);
        repw_input_layout.setPasswordVisibilityToggleEnabled(true);

        // PW 폰트 타입
        EditText pw_font = pw_input_layout.getEditText();
        pw_font.setTypeface(Typeface.DEFAULT);
        pw_font.setTransformationMethod(new PasswordTransformationMethod());
        // PW re 폰트 타입
        EditText repw_font = repw_input_layout.getEditText();
        repw_font.setTypeface(Typeface.DEFAULT);
        repw_font.setTransformationMethod(new PasswordTransformationMethod());

    }
}
