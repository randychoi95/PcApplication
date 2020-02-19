package com.ezen.MyPcApplication.First_View;

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

public class FindIdActivity extends AppCompatActivity {

    Button button_ok;
    EditText find_edit_name;
    EditText find_edit_phone;

    FirebaseAuth auth;
    FirebaseFirestore db;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_findid);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        find_edit_name = findViewById(R.id.find_edit_name);
        find_edit_phone = findViewById(R.id.find_edit_phone);

        button_ok = findViewById(R.id.find_btn_ok);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findID();
            }
        });

    }

    // 아이디 찾기
    private void findID(){
        String name = find_edit_name.getText().toString();
        String phone = find_edit_phone.getText().toString();

        db.collection("Member").whereEqualTo("name", name).whereEqualTo("phone", phone)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {
                    Log.e("ad", "실패");
                    Toast.makeText(FindIdActivity.this, "입력한 정보와 일치하는 계정이 없습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                        FindIdItem users = snapshot.toObject(FindIdItem.class);
                        email = users.getId();
                        Toast.makeText(getApplicationContext(), email, Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }
        });

    } // onCrate

}// Class
