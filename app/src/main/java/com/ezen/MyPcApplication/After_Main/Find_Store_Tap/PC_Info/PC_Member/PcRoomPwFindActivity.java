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
import com.google.firebase.firestore.QuerySnapshot;

public class PcRoomPwFindActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();

    EditText name_edit;
    EditText id_edit;
    EditText phone_edit;

    Button button_ok;
    Button button_cancel;

    FirebaseFirestore db;
    String pc_id;
    String pcname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_room_pw_find);

        // firebase
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // pc방 이름 가져오기
        Intent intent = getIntent();
        pcname = intent.getStringExtra("pcname");

        name_edit = findViewById(R.id.name_edit);
        id_edit = findViewById(R.id.id_edit);
        phone_edit = findViewById(R.id.phone_edit);

       // 비밀번호 찾기 확인버튼
        button_ok = findViewById(R.id.find_btn_ok);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPcPW();
            }
        });

      // 비밀번호 찾기 취소 버튼
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

    // 비밀번호 확인하고 맞으면 PcRoomPwResetActivity에서 PW 재입력 저장.
    private void findPcPW() {

        String name = name_edit.getText().toString();
        pc_id = id_edit.getText().toString();
        String phone = phone_edit.getText().toString();

        db.collection("PcMember").whereEqualTo("pcname", pcname).whereEqualTo("name", name).whereEqualTo("id", pc_id).whereEqualTo("phone", phone)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {
                    Log.e("ad", "실패");
                    Toast.makeText(PcRoomPwFindActivity.this, "입력한 정보와 일치하는 계정이 없습니다.", Toast.LENGTH_LONG).show();
                } else {

                    Intent intent = new Intent(getApplicationContext(), PcRoomPwResetActivity.class);
                    intent.putExtra("name", pcname);
                    intent.putExtra("id", pc_id);
                    startActivity(intent);
                    finish();

                    Toast.makeText(PcRoomPwFindActivity.this, "PC방 비밀번호를 다시 설정하세요.", Toast.LENGTH_LONG).show();

                }
            }
        });
    } //findPcPW

}//class
