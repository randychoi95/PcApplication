package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Review;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.StoreAdapter;
import com.ezen.MyPcApplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Pc_Review_TabFragment extends Fragment {

    RecyclerView list_review;
    Pc_Review_Adapter pc_review_adapter;

    FirebaseAuth auth;
    FirebaseFirestore db;
    FirebaseUser user;

    String name;
    String comments;
    String pcName;

    SimpleDateFormat dateFormat = new SimpleDateFormat ( "yyyy-MM-dd");
    Date time = new Date();
    String date = dateFormat.format(time);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_pc_review_tab, container, false);

        // 액티비티에서 데이터 받기
        Bundle extra = this.getArguments();
        if(extra != null) {
            extra = getArguments();
            pcName = extra.getString("name");
        }

// Firebase
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        user = auth.getCurrentUser();

// 현재 로그인된 사용자의 이름을 가져오는 함수
        getUserName();

        list_review = rootView.findViewById(R.id.list_notice);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list_review.setLayoutManager(layoutManager);

// 아답터 클래스 생성
        pc_review_adapter = new Pc_Review_Adapter();
        list_review.setAdapter(pc_review_adapter);

        Button btn_review = rootView.findViewById(R.id.btn_review);
        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Pc_Review_WriteActivity.class);
                startActivityForResult(intent, 100); // 결과값을 받을 코드를 넘겨줌.
            }
        });

// DB 컬랙션(reView)에 저장된 필드를 가져와서 리스트뷰에 뿌려줌.
        db.collection("reView").whereEqualTo("paName",pcName).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot snapshots : queryDocumentSnapshots) {
                    Pc_Review_AdapterItem adapterItem = snapshots.toObject(Pc_Review_AdapterItem.class);
                    pc_review_adapter.addItem(new Pc_Review_AdapterItem(adapterItem.getName(), adapterItem.getDate(), adapterItem.getComments(), R.drawable.user));
                }
                pc_review_adapter.Reverse();

                pc_review_adapter.notifyDataSetChanged(); // 부분 갱신

            }
        });


        return  rootView;
    }
//                                                   /////////////////////////////
//                                                   /////// onCreateView End//////
//                                                   //////////////////////////////

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

// WriteActivity에서 리뷰텍스트 내용을 받아서 리스트뷰 추가
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && data != null){
            comments = data.getStringExtra("listUpdate");
            pc_review_adapter.addItem(new Pc_Review_AdapterItem(name, date, comments, R.drawable.user));
            pc_review_adapter.Reverse();
            pc_review_adapter.notifyDataSetChanged(); // 부분 갱신
            doAdd();

        }

    }


// 현재 로그인된 사용자의 uid를 컬랙션(Member) 도큐먼트(uid)와 비교해서 필드(name) 가져오기.
    public void getUserName() {
        String uid = user.getUid();
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
                            List<Pc_Review_uidItem> ReviewItemList = task.getResult().toObjects(Pc_Review_uidItem.class);
                            for(Pc_Review_uidItem list : ReviewItemList){
                                // 현재 사용자 uid와 리스트에 저장된 uid 비교해서 값을 가져옴
                                if(user.getUid().equals(list.getUid())){
                                    name = list.getName();
                                    Log.e("test", name);
                                }
                            }
                        }else {
                            // 리스트 가져오기 실패
                            Log.e("Activity", "리스트 가져오기 실패");
                        }
                    }
                });

    }


// DB collection(reView)에 저장
    private void doAdd(){

        Pc_Review_FirebaseItem pc_review_item = new Pc_Review_FirebaseItem(name, date, comments, pcName, R.drawable.user);

        // add()함수를 사용하면, auto ID가 자동으로 발급됨.
        db.collection("reView")
                .add(pc_review_item)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.e("Activity", "DB쓰기 성공");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Activity", "DB쓰기 실패");
                    }
                });

    }



}
