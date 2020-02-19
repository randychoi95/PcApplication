package com.ezen.MyPcApplication.Side_Navigation.Person;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ezen.MyPcApplication.After_Main.Notice_Tap.Noticeitem;
import com.ezen.MyPcApplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Person_InfoFragment extends Fragment implements PersonInfoAdapter.setClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance(); // DB
    FirebaseAuth auth = FirebaseAuth.getInstance();  // 파이어베이스 인증
    FirebaseUser currentUser = auth.getInstance().getCurrentUser(); // 현재 사용자의 계정 데이타

    RecyclerView list_info;
    PersonInfoAdapter personInfoAdapter;

    EditText editPhone;
    TextView email_Text;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_person_info, container, false);

        editPhone = rootView.findViewById(R.id.editPhone);
        email_Text = rootView.findViewById(R.id.email_Text);
        String email = currentUser.getEmail();
        email_Text.setText(email);

        // 정보변경 버튼
        Button btn_convert = rootView.findViewById(R.id.btn_convert);
        btn_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personInfoUpdate();
            }
        });

        list_info = rootView.findViewById(R.id.list_info);

        // 레이아웃 인플레이터를 이용하여, 뷰를 리사이클러뷰에 바로 제공
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list_info.setLayoutManager(layoutManager);

        // 아답터 클래스 생성
        personInfoAdapter = new PersonInfoAdapter(this);
        personInfoAdapter.addItem(new PersonInfoItem("비밀번호 변경"));
        personInfoAdapter.addItem(new PersonInfoItem("회원탈퇴"));

        list_info.setAdapter(personInfoAdapter); // 아답터 연결

        // DB 정보 불러오기
        db.collection("Member")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.e("Activity", "리스트 가져오기");
                        if (task.isSuccessful()) {
                            // 리스트 가져오기 성공
                            Log.e("Activity", "리스트 가져오기 성공");
                            List<PersonInfoItem> personItemList = task.getResult().toObjects(PersonInfoItem.class);
                            for(PersonInfoItem list : personItemList){
                                // 현재 사용자 uid와 리스에 저장된 uid 비교해서 값을 가져옴
                                if(currentUser.getUid().equals(list.getUid())){
                                    editPhone.setText(list.getPhone());
                                }
                            }
                        }else {
                            // 리스트 가져오기 실패
                            Log.e("Activity", "리스트 가져오기 실패");
                        }
                    }
                });

        return rootView;
    }

    @Override
    public void setClick(Intent intent) {
        startActivity(intent);
    }

    // 개인정보 변경
    public void personInfoUpdate() {
            String userID = auth.getCurrentUser().getUid();

            db.collection("Member").document(userID).update("phone", editPhone.getText().toString());
            Toast.makeText(getContext(), "정보변경이 완료되었습니다.", Toast.LENGTH_SHORT).show();
    }



}
