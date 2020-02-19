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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class PcRoomLoginActivity extends AppCompatActivity {

    // PC방 회원들 리스트
    static List<PcMemberDTO> pcMemberDTOS = new ArrayList<PcMemberDTO>();
    // PC방 한 회원 정보
    PcMemberDTO singleMemberDTO;
    // 아이디 구분 변수
    int idflag;
    // 나이 구분 변수
    Boolean boolflag;

    // 아이디 관련
    TextInputLayout pcroom_id_input;
    TextInputLayout pcroom_pw_input;

    // 비밀번호 관련
    EditText pcroom_id_edit;
    EditText pcroom_pw_edit;

    // firebase 관련
    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    FirebaseUser currentUser;

    // 피시방 이름 변수
    String pcname;

;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_room_login);

        // firebase 정보 가져오기
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        // 생성 시 피시방 회원 테이블 가져오기
        db.collection("PcMember")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            // 리스트 가져오기 성공
                            pcMemberDTOS = task.getResult().toObjects(PcMemberDTO.class);
                        } else {
                            // 리스트 가져오기 실패
                            Log.e("Activity", "리스트 가져오기 실패");
                        }
                    }
                });

        // 툴바에 뒤로가기 백버튼 생성
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 피시방 이름받기
        Intent intent = getIntent();
        pcname = intent.getStringExtra("name");

        // 피시방 이름설정
        TextView textView = findViewById(R.id.textView2);
        textView.setText(pcname);

        // ID TextInputLayout
        pcroom_id_input = findViewById(R.id.pcroom_id_input);
        // ID InputEditText
        pcroom_id_edit = pcroom_id_input.getEditText();

        // PW TextInputLayout
        pcroom_pw_input = findViewById(R.id.pcroom_pw_input);
        // 비밀번호 보이기/안보이기
        pcroom_pw_input.setPasswordVisibilityToggleEnabled(true);
        // PW InputEditText
        pcroom_pw_edit = pcroom_pw_input.getEditText();

        // pc방 로그인
        Button btn_Pclogin = findViewById(R.id.btn_Pclogin);
        btn_Pclogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doPclogin();
            }
        });

        // pc방 회원가입
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


    public void doPclogin() {
        //예외처리
        if (pcroom_id_edit == null || pcroom_id_edit.getText().toString().length() < 1) {
            Toast.makeText(this, "아이디를 입력하세요", Toast.LENGTH_LONG).show();
            return;
        }
        if (pcroom_pw_edit == null || pcroom_pw_edit.getText().toString().length() < 1) {
            Toast.makeText(this, "비밀번호를 입력하세요", Toast.LENGTH_LONG).show();
            return;
        }

        // pc방 아이디가 있으면 singleMemberDTO에 저장하고 idflag 1로 지정, 아니면 idflag 0으로 지정
        for (int i = 0; i < pcMemberDTOS.size(); i++) {
            if (pcMemberDTOS.get(i).getId().equals(pcroom_id_edit.getText().toString())) {
                singleMemberDTO = pcMemberDTOS.get(i);
                idflag = 1;
                break;
            } else {
                idflag = 0;
            }
        }

        // pc방 아이디가 있으면
        if (idflag == 1) {
            // 아이디 비밀번호가 맞으면
            if (pcroom_id_edit.getText().toString().equals(singleMemberDTO.getId()) && pcroom_pw_edit.getText().toString().equals(singleMemberDTO.getPw())) {
                // 해당 pc방 회원이면
                if (pcname.equals(singleMemberDTO.getPcname())) {
                    // 청소년인지 성인인지 판별
                    doCheckBirth(singleMemberDTO.getBirth());
                    // 청소년이면
                    if (boolflag == false) {
                        Toast.makeText(getApplicationContext(), "21:00 ~ 09:00까지 청소년은 예약하실 수 없습니다.", Toast.LENGTH_SHORT).show();
                    // 성인이면
                    } else {
                        pcroom_id_edit.setText("");
                        pcroom_pw_edit.setText("");
                        Intent intent = new Intent(getApplicationContext(), ReservationActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                    }
                // 해당 pc방 회원 x
                } else {
                    Toast.makeText(getApplicationContext(), "회원이 아니거나 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            // 아이디 비밀번호 잘못 입력 시
            } else {
                Toast.makeText(getApplicationContext(), "회원이 아니거나 다시 입력해주세요", Toast.LENGTH_SHORT).show();
            }
        // pc방 아이디 없을 시
        } else {
            Toast.makeText(getApplicationContext(), "회원이 아니거나 다시 입력해주세요", Toast.LENGTH_SHORT).show();
        }
    }


    // 청소년 제한시간 체크
    public void doCheckBirth(String birth){
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Seoul");
        Calendar cal = Calendar.getInstance(timeZone);
        int year = cal.get(cal.YEAR);
        int hour = cal.get ( cal.HOUR_OF_DAY );

        // 청소년 출입제한시간 21:00 ~ 09:00
        if(21 <= hour || hour < 9){
            // 19세 미만(우리나라 나이로 계산)
            if( (year - Integer.parseInt(birth.substring(0,4)) < 19) ) {
                boolflag = false;
            } else {
                boolflag = true;
            }
        } else {
            boolflag = true;
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
