package com.ezen.MyPcApplication.First_View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
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
    Button button_cansel;
    EditText find_edit_name;
    EditText find_edit_phone;

    FirebaseAuth auth;
    FirebaseFirestore db;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_findid);

        // firebase
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        find_edit_name = findViewById(R.id.find_edit_name);
        find_edit_phone = findViewById(R.id.find_edit_phone);

        // 아이디 찾기 버튼
        button_ok = findViewById(R.id.find_btn_ok);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findID();
            }
        });

        // 아이디 찾기 취소 버튼
        button_cansel = findViewById(R.id.find_btn_cancel);
        button_cansel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
                    Toast.makeText(FindIdActivity.this, "입력한 정보와 일치하는 계정이 없습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                        FindIdItem users = snapshot.toObject(FindIdItem.class);
                        email = users.getId();
                        showDialog();
                    }
                }
            }
        });
    } // findID

    // 아이디 다이얼로그
    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("회원님의 아이디는 ").setMessage(email + "입니다");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
            @Override public void onShow(DialogInterface arg0) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK); } });
        alertDialog.show();
    } // showDialog

}// Class
