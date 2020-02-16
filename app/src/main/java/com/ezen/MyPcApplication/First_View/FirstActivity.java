package com.ezen.MyPcApplication.First_View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ezen.MyPcApplication.After_Main.MainActivity;
import com.ezen.MyPcApplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirstActivity extends AppCompatActivity {

    TextInputLayout id_text_input;
    TextInputLayout pw_text_input;

    EditText id_edit_input;
    EditText pw_edit_input;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            //이미 로그인 되었다면 이 액티비티를 종료함
            finish();
            //그리고 profile 액티비티를 연다.
            startActivity(new Intent(getApplicationContext(), MainActivity.class)); //추가해 줄 ProfileActivity
        }

        // PW TextInputLayout
        id_text_input = findViewById(R.id.id_text_input);
        // TextInputEditText
        id_edit_input = id_text_input.getEditText();

        // PW TextInputLayout
        pw_text_input = findViewById(R.id.pw_text_input);
        // 비밀번호 보이기/안보이기
        pw_text_input.setPasswordVisibilityToggleEnabled(true);
        // TextInputEditText
        pw_edit_input = pw_text_input.getEditText();

        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
                doLogin();
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
    }//End onCreate

    private void doLogin(){

        EditText id_edit_text = findViewById(R.id.id_edit_text);
        EditText pw_edit_text = findViewById(R.id.pw_edit_text);

        //예외처리
        if( id_edit_text == null || id_edit_text.getText().toString().length() < 1) {
            Toast.makeText(this, "아이디를 입력하세요", Toast.LENGTH_LONG).show();
            return;
        }
        if( pw_edit_text == null || pw_edit_text.getText().toString().length() < 1) {
            Toast.makeText(this, "비밀번호를 입력하세요", Toast.LENGTH_LONG).show();
            return;
        }

        String email = id_edit_text.getText().toString();
        String password = pw_edit_text.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if( task.isSuccessful() ){
                            //로그인 성공함
                            doEmailVerified();
                        }else{
                            //로그인 실패함
                            Toast.makeText(FirstActivity.this,
                                    "이메일 또는 암호가 맞지 않거나 회원가입을 하세요.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }//doLoin

    public void doEmailVerified(){
        user = firebaseAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            if(user.isEmailVerified()) {
                                finish();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "로그인 인증이 필요합니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

}//class
