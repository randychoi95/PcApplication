package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ezen.MyPcApplication.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class PcRoomIdFindActivity extends AppCompatActivity {


    Button button_ok;
    Button button_cancel;
    EditText name_edit;
    EditText phone_edit;

    FirebaseAuth auth;
    FirebaseFirestore db;
    String email;
    String pcname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_room_id_find);

        // firebase
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // pc방 이름 받기
        Intent intent = getIntent();
        pcname = intent.getStringExtra("pcname");

        name_edit = findViewById(R.id.name_edit);
        phone_edit = findViewById(R.id.phone_edit);

        // 확인 버튼
        button_ok = findViewById(R.id.find_btn_ok);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPcID();
            }
        });

        // 취소 버튼
        button_cancel = findViewById(R.id.find_btn_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PcRoomLoginActivity.class);
                intent.putExtra("name", pcname);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK   |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }//oncreate

    // 아이디 찾기 메소드
    private void findPcID(){
        String name = name_edit.getText().toString();
        String phone = phone_edit.getText().toString();

        db.collection("PcMember").whereEqualTo("pcname", pcname).whereEqualTo("name", name).whereEqualTo("phone", phone)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {
                    Log.e("ad", "실패");
                    Toast.makeText(PcRoomIdFindActivity.this, "입력한 정보와 일치하는 계정이 없습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                        PcMemberDTO users = snapshot.toObject(PcMemberDTO.class);
                        email = users.getId();
                        Toast.makeText(getApplicationContext(), email, Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }
        });
    }
}//End class

