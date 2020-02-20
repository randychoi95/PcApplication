package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ezen.MyPcApplication.First_View.FindPwActivity;
import com.ezen.MyPcApplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class PcRoomPwFindActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();

    EditText name_edit;
    EditText id_edit;
    EditText phone_edit;

    Button button_ok;
    Button button_cancel;

    FirebaseFirestore db;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_room_pw_find);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

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
                startActivity(intent);
            }
        });
    }//oncreate

    // 비밀번호 확인하고 맞으면 PcRoomPwResetActivity에서 PW 재입력 저장.
    private void findPcPW() {

        String name = name_edit.getText().toString();
        String id = id_edit.getText().toString();
        String phone = phone_edit.getText().toString();

        db.collection("PcMember").whereEqualTo("name", name).whereEqualTo("id", id).whereEqualTo("phone", phone)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {
                    Log.e("ad", "실패");
                    Toast.makeText(PcRoomPwFindActivity.this, "입력한 정보와 일치하는 계정이 없습니다.", Toast.LENGTH_LONG).show();
                } else {

                    Intent intent = new Intent(getApplicationContext(), PcRoomPwResetActivity.class);
                    startActivity(intent);

                    Toast.makeText(PcRoomPwFindActivity.this, "PC방 비밀번호를 다시 설정하세요.", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

}//class
