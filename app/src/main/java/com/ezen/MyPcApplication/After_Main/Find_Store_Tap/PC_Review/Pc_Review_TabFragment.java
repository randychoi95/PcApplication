package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Review;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ezen.MyPcApplication.R;


public class Pc_Review_TabFragment extends Fragment {

    RecyclerView list_review;
    Pc_Review_Adapter pc_review_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_pc_review_tab, container, false);

        list_review = rootView.findViewById(R.id.list_notice);

        // 레이아웃 인플레이터를 이용하여, 뷰를 리사이클러뷰에 바로 제공
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list_review.setLayoutManager(layoutManager);

        // 아답터 클래스 생성
        pc_review_adapter = new Pc_Review_Adapter();
        pc_review_adapter.addItem(new Pc_Review_Item("Ezen", "2020.02.06", "게임이 렉없이 돌아가요.", R.drawable.user));
        pc_review_adapter.addItem(new Pc_Review_Item("Ezen", "2019.06.05", "음식이 맛있어요", R.drawable.user));


        list_review.setAdapter(pc_review_adapter);

        Button btn_review = rootView.findViewById(R.id.btn_review);
        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Pc_Review_WriteActivity.class);
                startActivityForResult(intent, 100); // 결과값을 받을 코드를 넘겨줌.
            }
        });

        return  rootView;
    }

    @Override // WriteActivity에서 데이타를 받아서 리스트뷰 추가
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && data != null){

            String review = data.getStringExtra("listUpdate");
            pc_review_adapter.addItem(new Pc_Review_Item("Ezen", "2020.02.06", review, R.drawable.user));
            pc_review_adapter.notifyDataSetChanged(); // 부분 갱신
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

}
