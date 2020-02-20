package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Review;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ezen.MyPcApplication.First_View.FirstActivity;
import com.ezen.MyPcApplication.First_View.JoinItem;
import com.ezen.MyPcApplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Pc_Review_WriteActivity extends AppCompatActivity {

    TextInputLayout id_text_input;
    Button btn_review_resgister;
    EditText editText_review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_review_write);

        id_text_input = findViewById(R.id.id_text_input);
        editText_review = id_text_input.getEditText();

        // 등록하기 버튼 이벤트
        btn_review_resgister = findViewById(R.id.btn_review_resgister);
        btn_review_resgister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comments = editText_review.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("listUpdate", comments);
                setResult(100, intent); //  onActivityResult()가 호출됨.
                Toast.makeText(getApplicationContext(), "등록되었습니다", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        // 툴바에 뒤로가기 백버튼 생성
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
