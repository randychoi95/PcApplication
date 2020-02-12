package com.ezen.MyPcApplication.After_Main.Notice_Tap;

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
import android.widget.Toast;

import com.ezen.MyPcApplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class NoticeFragment extends Fragment implements NoticeAdapter.setClickListener {

    RecyclerView list_notice;
    NoticeAdapter noticeAdapter;

    FirebaseFirestore db = FirebaseFirestore.getInstance(); // DB
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();  // 파이어베이스 인증

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 레이아웃 인플레이터를 이용하여, 뷰를 리사이클러뷰에 바로 제공
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_notice, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        // 리사이클러뷰
        list_notice = rootView.findViewById(R.id.list_notice);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list_notice.setLayoutManager(layoutManager);


        // 아답터 클래스 생성
        noticeAdapter = new NoticeAdapter(this);

        db.collection("notice")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.e("Activity", "리스트 가져오기");
                        if (task.isSuccessful()) {
                            // 리스트 가져오기 성공
                            Log.e("Activity", "리스트 가져오기 성공");
                            List<Noticeitem> noticeList = task.getResult().toObjects(Noticeitem.class);
                            for ( Noticeitem noticeitem : noticeList) {
                                noticeAdapter.addItem( noticeitem );
                            }

                            list_notice.setAdapter(noticeAdapter); // 아답터 연결

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

}
