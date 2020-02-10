package com.ezen.MyPcApplication.Side_Navigation.Login;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ezen.MyPcApplication.R;

public class Login_CheckFragment extends Fragment {

    RecyclerView list_login;
    LoginCheckAdapter loginCheckAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_login_check, container, false);

        list_login = rootView.findViewById(R.id.list_login);

        // 레이아웃 인플레이터를 이용하여, 뷰를 리사이클러뷰에 바로 제공
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list_login.setLayoutManager(layoutManager);

        // 아답터 클래스 생성
        loginCheckAdapter = new LoginCheckAdapter();
        loginCheckAdapter.addItem(new LoginCheckItem("2020.02.05", "파브 PC방 의정부점"));
        loginCheckAdapter.addItem(new LoginCheckItem("2020.02.05", "파브 PC방 의정부점"));
        loginCheckAdapter.addItem(new LoginCheckItem("2020.02.05", "파브 PC방 의정부점"));

        list_login.setAdapter(loginCheckAdapter);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

}
