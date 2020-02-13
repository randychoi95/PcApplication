package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Reservation.ReservationActivity;
import com.ezen.MyPcApplication.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class PcRoomLoginActivity extends AppCompatActivity {

    TextInputLayout pcroom_id_input;
    TextInputLayout pcroom_pw_input;

    EditText pcroom_id_edit;
    EditText pcroom_pw_edit;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_room_login);

        // 툴바에 뒤로가기 백버튼 생성
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // PW TextInputLayout
        pcroom_id_input = findViewById(R.id.pcroom_id_input);
        // TextInputEditText
        pcroom_id_edit = pcroom_id_input.getEditText();

        // PW TextInputLayout
        pcroom_pw_input = findViewById(R.id.pcroom_pw_input);
        // 비밀번호 보이기/안보이기
        pcroom_pw_input.setPasswordVisibilityToggleEnabled(true);
        // TextInputEditText
        pcroom_pw_edit = pcroom_pw_input.getEditText();

        Button btn_Pclogin = findViewById(R.id.btn_Pclogin);
        btn_Pclogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doPclogin();
            }
        });

        Button btn_Pcjoin = findViewById(R.id.btn_Pcjoin);
        btn_Pcjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PcRoomJoinActivity.class));
            }
        });
    }

    public void doPclogin(){
        // 로그인
        startActivity(new Intent(getApplicationContext(), ReservationActivity.class));
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
