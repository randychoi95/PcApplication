package com.ezen.MyPcApplication.First_View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ezen.MyPcApplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class JoinActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    FirebaseUser user;

    TextInputLayout id_input_layout;
    TextInputLayout pw_input_layout;
    TextInputLayout repw_input_layout;
    TextInputLayout phone_input_layout;
    TextInputLayout name_input_layout;

    EditText member_id;
    EditText member_pw;
    EditText member_repw;
    EditText member_phone;
    EditText member_name;

    TextView pw_check;

    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        // firebase
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        user = firebaseAuth.getCurrentUser();

        // 이름 텍스트
        name_input_layout = findViewById(R.id.name_input_layout);
        member_name = name_input_layout.getEditText();

        // 이메일 텍스트
        id_input_layout = findViewById(R.id.id_input_layout);
        member_id = id_input_layout.getEditText();

        // 비밀번호 텍스트
        pw_input_layout = findViewById(R.id.pw_input_layout);
        member_pw = pw_input_layout.getEditText();
        member_pw.setTypeface(Typeface.DEFAULT);  // 폰트 꺠짐 방지
        member_pw.setTransformationMethod(new PasswordTransformationMethod());

        // 비밀번호 재입력 텍스트
        repw_input_layout = findViewById(R.id.repw_input_layout);
        member_repw = repw_input_layout.getEditText();
        member_repw.setTypeface(Typeface.DEFAULT);  // 폰트 꺠짐 방지
        member_repw.setTransformationMethod(new PasswordTransformationMethod());

        // 핸드폰 번호 텍스트
        phone_input_layout = findViewById(R.id.phone_input_layout);
        member_phone = phone_input_layout.getEditText();

        // 회원가입 버튼
        Button btn_join = findViewById(R.id.btn_join);
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doJoin();
            }
        });
    } // onCreate

    // 회원가입 메소드
    public void doJoin(){
        email = member_id.getText().toString().trim();
        password = member_pw.getText().toString().trim();

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

        if (member_repw == null || member_repw.getText().toString().length() < 1) {
            Toast.makeText(this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        if (member_phone.getText().toString().length() != 11 ||
                member_phone.getText().toString().contains("-")) {
            Toast.makeText(this, "전화번호를 다시 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        // 회원가입
        if(member_pw.getText().toString().equals(member_repw.getText().toString())) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                doAdd();
                            } else {
                                Toast.makeText(getApplicationContext(), "이메일형식으로 입력하세요", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            pw_check.setTextColor(Color.RED);
            pw_check.setText("비밀번호가 일치하지 않습니다.");
        }

    } // doJoin

    // DB collection에 저장
    private void doAdd(){
        String id = firebaseAuth.getCurrentUser().getEmail(); // 회원가입한 사용자 이메일
        String uid = firebaseAuth.getCurrentUser().getUid();  // 회원가입한 사용자 uid
        String phone = member_phone.getText().toString();
        String name = member_name.getText().toString();

        JoinItem joinItem = new JoinItem(id, phone, uid, name);

        db.collection("Member").document(uid).set(joinItem)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "회원가입성공", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK   |
                                        Intent.FLAG_ACTIVITY_SINGLE_TOP |
                                        Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                        finish();
                    }
                }).
                addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "6자리미만,이메일 형식이 아니거나, 이미 등록된 아이디, 통신 오류를 확인 하세요.", Toast.LENGTH_SHORT).show();
                    }
                });

    } // doAdd

}// Class
