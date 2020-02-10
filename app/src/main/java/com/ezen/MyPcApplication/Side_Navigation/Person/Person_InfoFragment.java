package com.ezen.MyPcApplication.Side_Navigation.Person;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ezen.MyPcApplication.R;

public class Person_InfoFragment extends Fragment implements PersonInfoAdapter.setClickListener {

    RecyclerView list_info;
    PersonInfoAdapter personInfoAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_person_info, container, false);

        list_info = rootView.findViewById(R.id.list_info);

        // 레이아웃 인플레이터를 이용하여, 뷰를 리사이클러뷰에 바로 제공
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list_info.setLayoutManager(layoutManager);

        // 아답터 클래스 생성
        personInfoAdapter = new PersonInfoAdapter(this);
        personInfoAdapter.addItem(new PersonInfoItem("비밀번호 변경"));
        personInfoAdapter.addItem(new PersonInfoItem("회원탈퇴"));

        list_info.setAdapter(personInfoAdapter);

        return rootView;
    }

    @Override
    public void setClick(Intent intent) {
        startActivity(intent);
    }

}
