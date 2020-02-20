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

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class PcRoomPwResetActivity extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseUser currentUser;
    FirebaseAuth auth;
//    TextInputLayout password_input;
//    TextInputLayout password_input2;
    EditText password_edit;
    EditText password_edit2;
    String password;
//    String email = currentUser.getEmail();
    Button button_ok;
    Button button_cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_room_pw_reset);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        Button button_ok = findViewById(R.id.find_btn_ok);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                doAdd();
            }
        });


        // 비밀번호 입력하는거 두개 변수선언

        password_edit = findViewById(R.id.password_edit);

        password_edit2 = findViewById(R.id.password_edit2);
        // 일치하면 테이블에 들어가는 함수 생성호출

        if( password_edit == password_edit2 ){

        }

    }//oncreate

    private void doAdd(){

        // 비밀번호 변수선언
        String pw = password_edit.getText().toString();
        String pw2 = password_edit2.getText().toString();

        // DTO가서 비밀번호만 있는 생성자 만들고

        // 생성자로 비밀번호만 들어가게 만들어주고

        PcMemberDTO userDTO = new PcMemberDTO(/* q비밀번호 변수*/);

        //add()함수를 사용하면, auto ID가 자동으로 발급됨.
//        String autoID = db.collection("users").document().getId();
        db.collection("PcMember")
                .add(userDTO)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.e("Activity", "DB쓰기 성공: "+ documentReference.getId() );

                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e){
                        Log.e("Activity","DB쓰기 실패: " + e);
                    }
                });
    }//doAdd

}//class
