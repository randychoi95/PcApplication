package com.ezen.MyPcApplication.After_Main.Find_Store_Tap;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ezen.MyPcApplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class StoreFragment extends Fragment implements StoreAdapter.setClickListener{

    RecyclerView list_pc;
    StoreAdapter storeAdapter;

    FirebaseFirestore db;
    FirebaseAuth auth;
    String email;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
//        email = auth.getCurrentUser().getEmail();
        // LayoutInflator 클래스 : XML 뷰파일을 실제 뷰객체로 만들어 주는 시스템 함수
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_store, container, false);

        list_pc = rootView.findViewById(R.id.list_info);

        // 레이아웃 인플레이터를 이용하여, 뷰를 리사이클러뷰에 바로 제공
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list_pc.setLayoutManager(layoutManager);

        // 아답터 클래스 생성
        storeAdapter = new StoreAdapter(this);
        db.collection("PcRoom")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    // 리스트 가져오기 성공
                    List<StoreItem> PcList = task.getResult().toObjects(StoreItem.class);
                    for( StoreItem pcItem : PcList ){
                        storeAdapter.addItem(pcItem);
                        Log.e("test1", "이름 : " + pcItem.getName());
                    }
                    list_pc.setAdapter(storeAdapter);
                } else {
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

}
