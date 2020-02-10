package com.ezen.MyPcApplication.First_View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ezen.MyPcApplication.After_Main.MainActivity;
import com.ezen.MyPcApplication.R;
import com.google.android.material.textfield.TextInputLayout;

public class FirstActivity extends AppCompatActivity {

    TextInputLayout id_text_input;
    TextInputLayout pw_text_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        // ID TextInputLayout
        id_text_input = findViewById(R.id.id_text_input);
        EditText id_edit_text = id_text_input.getEditText();
        id_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().contains("#")) {
                    id_text_input.setError("특수 문자는 사용할 수 없습니다.");
                } else {
                    id_text_input.setError(null); // null은 에러 메시지를 지워주는 기능
                }
            }
        });

        // PW TextInputLayout
        pw_text_input = findViewById(R.id.pw_text_input);
        // 비밀번호 보이기/안보이기
        pw_text_input.setPasswordVisibilityToggleEnabled(true);

        
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button btn_join = findViewById(R.id.btn_join);
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });

        TextView text_findID = findViewById(R.id.text_findID);
        text_findID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindIdActivity.class);
                startActivity(intent);
            }
        });

        TextView text_findPW = findViewById(R.id.text_findPW);
        text_findPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindPwActivity.class);
                startActivity(intent);
            }
        });

    }
}
