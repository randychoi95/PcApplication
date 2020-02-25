package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PcRoomPwResetActivity extends AppCompatActivity {
    static List<PcMemberDTO> pcMemberDTOS = new ArrayList<PcMemberDTO>();
    PcMemberDTO singleMemberDTO;

    TextInputLayout password_input;
    TextInputLayout password_input2;

    EditText password_edit;
    EditText password_edit2;

    TextView pwCheck;

    FirebaseFirestore db;

    String pcname;
    String pc_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_room_pw_reset);

        // firebase
        db = FirebaseFirestore.getInstance();
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

        // 이름 아이디 정보 가져오기
        Intent intent = getIntent();
        pcname = intent.getStringExtra("name");
        pc_id = intent.getStringExtra("id");

        password_input = findViewById(R.id.password_input);
        password_input2 = findViewById(R.id.password_input2);

        password_edit = password_input.getEditText();
        password_edit.setTypeface(Typeface.DEFAULT);  // 폰트 꺠짐 방지
        password_edit.setTransformationMethod(new PasswordTransformationMethod());

        password_edit2 = password_input2.getEditText();
        password_edit2.setTypeface(Typeface.DEFAULT);  // 폰트 꺠짐 방지
        password_edit2.setTransformationMethod(new PasswordTransformationMethod());

        pwCheck = findViewById(R.id.pwCheck);

        // 확인 버튼
        Button find_btn_ok = findViewById(R.id.find_btn_ok);
        find_btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doReset();
            }
        });

        // 비밀번호 찾기 취소 버튼
        Button button_cancel = findViewById(R.id.find_btn_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }//oncreate

    // 비밀번호 재설정 메소드
    public void doReset(){
        for(int i=0; i<pcMemberDTOS.size(); i++){
            if(pc_id.equals(pcMemberDTOS.get(i).getId())){
                singleMemberDTO = pcMemberDTOS.get(i);
                break;
            }
        }

        doDelete();

        if (password_edit == null || password_edit.getText().toString().length() < 6) {
            Toast.makeText(this, "비밀번호 6자리 이상 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        try{
            Thread.sleep(500);
            pwConvert();
        } catch(Exception e){}

    }// doReset

    // db에 재설정된 비밀번호 저장
    private void pwConvert(){
        if(password_edit.getText().toString().equals(password_edit2.getText().toString())){
            String pw = password_edit.getText().toString();

            PcMemberDTO pcMemberDTO = new PcMemberDTO(singleMemberDTO.getName(), singleMemberDTO.getId(), pw, singleMemberDTO.getBirth(), singleMemberDTO.getPhone(), pcname);

            //add()함수를 사용하면, auto ID가 자동으로 발급됨.
            db.collection("PcMember")
                    .add( pcMemberDTO )
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.e("Activity", "DB쓰기 성공:" + documentReference.getId() );
                            Intent intent = new Intent(getApplicationContext(), PcRoomLoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK   |
                                    Intent.FLAG_ACTIVITY_SINGLE_TOP |
                                    Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("Activity", "DB쓰기 실패:" + e );
                        }
                    });

        } else {
            pwCheck.setTextColor(Color.RED);
            pwCheck.setText("비밀번호가 일치하지 않습니다.");
        }
    }// pwConvert

    // 기존에 있던 정보 삭제
    private void doDelete(){
        db.collection("PcMember").whereEqualTo("id", pc_id).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot snapshot : queryDocumentSnapshots){
                    PcMemberDTO pcMemberDTO = snapshot.toObject(PcMemberDTO.class);
                    if(pcname.equals(pcMemberDTO.getPcname())){
                        String documentID = snapshot.getId();
                        db.collection("PcMember").document(documentID).delete();
                        Log.e("delete", "삭제 성공");
                        return;
                    }
                }
            }
        });
    }

}//class
