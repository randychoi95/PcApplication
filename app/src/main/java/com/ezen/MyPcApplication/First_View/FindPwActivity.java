package com.ezen.MyPcApplication.First_View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ezen.MyPcApplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class FindPwActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    TextInputLayout id_input;
    Button find_btn_ok;
    Button find_btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_findpw);

        // 비밀번호 찾기 확인버튼
        find_btn_ok = findViewById(R.id.find_btn_ok2);
        find_btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPW();
            }
        });

        // 비밀번호 찾기 취소 버튼
        find_btn_cancel = findViewById(R.id.find_btn_cancel2);
        find_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }//oncreate

    // 비밀번호 재설정 이메일 보내기.
    public void findPW() {
        id_input = findViewById(R.id.id_input_layout);
        EditText editText = id_input.getEditText();
        String email = editText.getText().toString();

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "재설정 이메일 전송완료 \n재설정 이메일을 통해 비밀번호를 변경해주세요!!", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                });

    }

}//class


