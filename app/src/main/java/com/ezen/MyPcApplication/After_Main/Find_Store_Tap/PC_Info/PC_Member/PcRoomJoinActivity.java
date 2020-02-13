package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
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

public class PcRoomJoinActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    TextInputLayout pcroom_userId_input;
    TextInputLayout pcroom_userName_input;
    TextInputLayout pcroom_userPw_input;
    TextInputLayout pcroom_userRepw_input;
    TextInputLayout pcroom_userBirth_input;
    TextInputLayout pcroom_userPhone_input;

    EditText userId;
    EditText userName;
    EditText userPw;
    EditText userRepw;
    EditText userBirth;
    EditText userPhone;

    TextView pw_check;
    TextView birth_check;
    TextView phone_check;

    String name;
    String id;
    String password;
    String birth;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_room_join);

        firebaseAuth = FirebaseAuth.getInstance();

        // id
        pcroom_userId_input = findViewById(R.id.pcroom_userId_input);
        userId = pcroom_userId_input.getEditText();

        // name
        pcroom_userName_input = findViewById(R.id.pcroom_userName_input);
        userName = pcroom_userName_input.getEditText();

        // pw
        pcroom_userPw_input = findViewById(R.id.pcroom_userPw_input);
        userPw = pcroom_userPw_input.getEditText();

        // repw
        pcroom_userRepw_input = findViewById(R.id.pcroom_userRepw_input);
        userRepw = pcroom_userRepw_input.getEditText();

        // birth
        pcroom_userBirth_input = findViewById(R.id.pcroom_userBirth_input);
        userBirth = pcroom_userBirth_input.getEditText();

        // phone
        pcroom_userPhone_input = findViewById(R.id.pcroom_userPhone_input);
        userPhone = pcroom_userPhone_input.getEditText();
        userPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        Button btn_Pcjoin = findViewById(R.id.btn_Pcjoin);
        btn_Pcjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doPcJoin();
            }
        });
    }

    public void doPcJoin(){
        name = userName.getText().toString().trim();
        id = userId.getText().toString().trim();
        password = userPw.getText().toString().trim();
        birth = userBirth.getText().toString().trim();
        phone = userPhone.getText().toString().trim();

        //예외처리
        if (userName == null || userName.getText().toString().length() < 1) {
            Toast.makeText(this, "이름을 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userId == null || userId.getText().toString().length() < 1) {
            Toast.makeText(this, "아이디를 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPw == null || userPw.getText().toString().length() < 1) {
            Toast.makeText(this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();

            return;
        }

        if (userBirth == null || userBirth.getText().toString().length() < 1) {
            Toast.makeText(this, "생일을 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPhone == null || userPhone.getText().toString().length() < 1) {
            Toast.makeText(this, "전화번호를 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        if(userPw.getText().toString().equals(userRepw.getText().toString())){
            pw_check = findViewById(R.id.pw_check);
            pw_check.setTextColor(Color.GREEN);
            pw_check.setText("비밀번호가 일치합니다.");
            if(userBirth.getText().toString().length() == 8){
                birth_check = findViewById(R.id.birth_check);
                birth_check.setText("");

                if(userPhone.getText().toString().length() == 13 ||
                        !userPhone.getText().toString().contains("-")){
                    finish();
                } else {
                    phone_check = findViewById(R.id.phone_check);
                    phone_check.setTextColor(Color.RED);
                    phone_check.setText("13자리가 아니거나 '-'빼고 입력해주세요.");
                }
            } else {
                birth_check = findViewById(R.id.birth_check);
                birth_check.setTextColor(Color.RED);
                birth_check.setText("20200101형식에 맞게 입력해주세요.");
            }
        } else {
            pw_check = findViewById(R.id.pw_check);
            pw_check.setTextColor(Color.RED);
            pw_check.setText("비밀번호가 다릅니다.");
        }
    }

}
