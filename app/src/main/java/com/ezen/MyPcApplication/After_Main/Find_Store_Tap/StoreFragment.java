package com.ezen.MyPcApplication.After_Main.Find_Store_Tap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ezen.MyPcApplication.R;


public class StoreFragment extends Fragment implements StoreAdapter.setClickListener{

    RecyclerView list_pc;
    StoreAdapter storeAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // LayoutInflator 클래스 : XML 뷰파일을 실제 뷰객체로 만들어 주는 시스템 함수
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_store, container, false);

        list_pc = rootView.findViewById(R.id.list_info);

        // 레이아웃 인플레이터를 이용하여, 뷰를 리사이클러뷰에 바로 제공
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list_pc.setLayoutManager(layoutManager);

        // 아답터 클래스 생성
        storeAdapter = new StoreAdapter(this);
        storeAdapter.addItem(new StoreItem("파브", "150m", "경기도 의정부시"));
        storeAdapter.addItem(new StoreItem("파브", "150m", "경기도 의정부시"));
        storeAdapter.addItem(new StoreItem("파브", "150m", "경기도 의정부시"));
        storeAdapter.addItem(new StoreItem("파브", "150m", "경기도 의정부시"));

        list_pc.setAdapter(storeAdapter);

        return rootView;
    }

    @Override
    public void setClick(Intent intent) {
        startActivity(intent);
    }
}
