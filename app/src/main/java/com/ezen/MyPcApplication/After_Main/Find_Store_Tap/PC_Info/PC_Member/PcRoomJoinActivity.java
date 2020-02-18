package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class PcRoomJoinActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    FirebaseUser currentUser;

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
    String checkId;
    String password;
    String birth;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_room_join);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

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
    } // onCreate

    // pc방 회원가입
    public void doPcJoin() {
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

        if (userPw.getText().toString().length() >= 6 &&
                userPw.getText().toString().equals(userRepw.getText().toString())) {
            pw_check = findViewById(R.id.pw_check);
            pw_check.setTextColor(Color.GREEN);
            pw_check.setText("비밀번호가 일치합니다.");
            if (userBirth.getText().toString().length() == 8) {
                if (checkBirth()) {
                    birth_check = findViewById(R.id.birth_check);
                    birth_check.setText("");
                    if (userPhone.getText().toString().length() == 13 ||
                            !userPhone.getText().toString().contains("-")) {
                        checkId();
//                            doAdd();
//                            Toast.makeText(this, "회원가입이 되었습니다.", Toast.LENGTH_SHORT).show();
//                            finish();
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
                birth_check = findViewById(R.id.birth_check);
                birth_check.setTextColor(Color.RED);
                birth_check.setText("20200101형식에 맞게 입력해주세요.");
            }
        } else {
            pw_check = findViewById(R.id.pw_check);
            pw_check.setTextColor(Color.RED);
            pw_check.setText("비밀번호가 일치하지 않거나 6자리 미만입니다.");
        }



    } // doPcJoin

    // 아이디 중복확인
    private void checkId(){
        checkId = userId.getText().toString().trim();
        db.collection("PcMember")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<PcMemberDTO> pcMemberDTOList = task.getResult().toObjects(PcMemberDTO.class);
                        for(PcMemberDTO pcMemberDTO : pcMemberDTOList){
                            if(pcMemberDTO.getId().equals(checkId)) {
                                Toast.makeText(getApplicationContext(), "중복된 아이디입니다.", Toast.LENGTH_SHORT).show();
                                break;
                            } else {
                                doAdd();
                                Toast.makeText(getApplicationContext(), "회원가입이 되었습니다.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }
                });
    }

    // 생년월일 체크
    private Boolean checkBirth() {
        String year = userBirth.getText().toString().substring(0, 4);
        String month = userBirth.getText().toString().substring(4, 6);
        String day = userBirth.getText().toString().substring(6);

        if (Integer.parseInt(year) <= Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul")).get(Calendar.YEAR)) {
            if (0 < Integer.parseInt(month) && Integer.parseInt(month) < 13) {
                if (((0 < Integer.parseInt(day) && Integer.parseInt(day) < 32))) {
                    return true;
                }
            }
        }

        return false;
    }

    // firebase database에 추가
    private void doAdd(){
        String name = userName.getText().toString();
        String id = userId.getText().toString();
        String pw = userPw.getText().toString();
        String birth = userBirth.getText().toString();
        String phone = userPhone.getText().toString();
        String uid = currentUser.getUid();
        String email = currentUser.getEmail();

        Intent intent = getIntent();
        String pcname = intent.getStringExtra("name");


        PcMemberDTO pcMemberDTO = new PcMemberDTO(name, id, pw, birth, phone, uid, email, pcname);

        // add()함수를 사용하면, auto ID가 자동으로 발급됨.
        db.collection("PcMember")
                .add(pcMemberDTO)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.e("Activity", "DB쓰기 성공:" + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Activity", "DB쓰기 실패:" + e);
                    }
                });
    }

} // class
