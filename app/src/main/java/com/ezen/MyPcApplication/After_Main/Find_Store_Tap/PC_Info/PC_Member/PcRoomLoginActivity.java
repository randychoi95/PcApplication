package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Reservation.ReservationActivity;
import com.ezen.MyPcApplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class PcRoomLoginActivity extends AppCompatActivity {

    TextInputLayout pcroom_id_input;
    TextInputLayout pcroom_pw_input;

    EditText pcroom_id_edit;
    EditText pcroom_pw_edit;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    FirebaseUser currentUser;

    String pcname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_room_login);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        // 툴바에 뒤로가기 백버튼 생성
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 피시방 이름받기
        Intent intent = getIntent();
        pcname = intent.getStringExtra("name");

        // 피시방 이름설정
        TextView textView = findViewById(R.id.textView2);
        textView.setText(pcname);

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
                Intent intent2 = new Intent(getApplicationContext(), PcRoomJoinActivity.class);
                intent2.putExtra("name", pcname);
                startActivity(intent2);
            }
        });
    }

    public void doPclogin(){
        //예외처리
        if( pcroom_id_edit == null || pcroom_id_edit.getText().toString().length() < 1) {
            Toast.makeText(this, "아이디를 입력하세요", Toast.LENGTH_LONG).show();
            return;
        }
        if( pcroom_pw_edit == null || pcroom_pw_edit.getText().toString().length() < 1) {
            Toast.makeText(this, "비밀번호를 입력하세요", Toast.LENGTH_LONG).show();
            return;
        }

        db.collection("PcMember")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            // 리스트 가져오기 성공
                            List<PcMemberDTO> pcMemberDTOList = task.getResult().toObjects(PcMemberDTO.class);
                            for( PcMemberDTO PcMemberDTO : pcMemberDTOList) {
                                if (doCheckBirth(PcMemberDTO.getBirth())) {
                                    if (pcname.equals(PcMemberDTO.getPcname())) {
                                        if (pcroom_id_edit.getText().toString().equals(PcMemberDTO.getId())
                                                && pcroom_pw_edit.getText().toString().equals(PcMemberDTO.getPw())) {
                                            finish();
                                            startActivity(new Intent(getApplicationContext(), ReservationActivity.class));
                                            Toast.makeText(getApplicationContext(), "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                                            break;
                                        } else {
                                            Toast.makeText(getApplicationContext(), "회원가입 또는 아이디나 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "회원가입 또는 아이디나 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "청소년은 21:00 ~ 09:00까지 예약이 안됩니다.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            // 리스트 가져오기 실패
                            Log.e("Activity", "리스트 가져오기 실패");
                        }
                    }
                });
    }

    // 청소년 제한시간 체크
    public boolean doCheckBirth(String birth){
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Seoul");
        Calendar cal = Calendar.getInstance(timeZone);
        int year = cal.get(cal.YEAR);
        int hour = cal.get ( cal.HOUR_OF_DAY );
        Log.e("year", Integer.toString(year));
        Log.e("min", Integer.toString(hour));

        if(21 <= hour && hour <= 9 && (year - Integer.parseInt(birth) < 19)){
            return false;
        } else {
            return true;
        }
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
