package com.ezen.MyPcApplication.First_View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ezen.MyPcApplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class JoinActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    TextInputLayout id_input_layout;
    TextInputLayout pw_input_layout;
    TextInputLayout repw_input_layout;

    EditText member_id;
    EditText member_pw;
    EditText member_repw;

    TextView pw_check;

//    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        firebaseAuth = FirebaseAuth.getInstance();

//        if(firebaseAuth.getCurrentUser() != null) {
//            // 이미 로그인 되었다면 이 액티비티를 종료
//            finish();
//            startActivity(new Intent(this, MainActivity.class));
//        }

        id_input_layout = findViewById(R.id.id_input_layout);
        member_id = id_input_layout.getEditText();

        pw_input_layout = findViewById(R.id.pw_input_layout);
        member_pw = pw_input_layout.getEditText();

        repw_input_layout = findViewById(R.id.repw_input_layout);
        member_repw = repw_input_layout.getEditText();

        member_repw.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력되는 텍스트에 변화가 있을 때

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // 입력이 끝났을 때
                pw_check = findViewById(R.id.pw_check);
                if(member_repw.getText().toString().equals(member_pw.getText().toString())){
                    pw_check.setTextColor(Color.GREEN);
                    pw_check.setText("비밀번호가 일치합니다.");
                } else {
                    pw_check.setTextColor(Color.RED);
                    pw_check.setText("비밀번호가 일치하지 않습니다.");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력하기 전에
            }
        });

        Button btn_join = findViewById(R.id.btn_join);
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doJoin();
            }
        });
    }

    public void doJoin(){
        String email = member_id.getText().toString().trim();
        String password = member_pw.getText().toString().trim();

        pw_check = findViewById(R.id.pw_check);

        //예외처리
        if (member_id == null || member_id.getText().toString().length() < 1) {
            Toast.makeText(this, "이메일로 아이디를 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        if (member_pw == null || member_pw.getText().toString().length() < 1) {
            Toast.makeText(this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

//        progressDialog.setMessage("등록중입니다.");
//        progressDialog.show();



        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "회원가입이 되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "6자리미만,이메일 형식이 아니거나, 이미 등록된 아이디, 통신 오류를 확인 하세요.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
