package com.ezen.MyPcApplication.Side_Navigation.Pay;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ezen.MyPcApplication.R;

public class Pay_CheckFragment extends Fragment {

    RecyclerView list_pay;
    PayCheckAdapter payCheckAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_pay_check, container, false);

        list_pay = rootView.findViewById(R.id.list_pay);

        // 레이아웃 인플레이터를 이용하여, 뷰를 리사이클러뷰에 바로 제공
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list_pay.setLayoutManager(layoutManager);

        // 아답터 클래스 생성
        payCheckAdapter = new PayCheckAdapter();
        payCheckAdapter.addItem(new PayCheckItem("2020.02.23", "6,800"));
        payCheckAdapter.addItem(new PayCheckItem("2020.02.19", "12,000"));
        payCheckAdapter.addItem(new PayCheckItem("2020.02.15", "3,000"));
        payCheckAdapter.addItem(new PayCheckItem("2020.02.11", "5,500"));
        payCheckAdapter.addItem(new PayCheckItem("2020.02.07", "7,800"));
        payCheckAdapter.addItem(new PayCheckItem("2020.02.04", "3,000"));
        payCheckAdapter.addItem(new PayCheckItem("2020.01.23", "1,000"));
        payCheckAdapter.addItem(new PayCheckItem("2020.01.22", "9,800"));
        payCheckAdapter.addItem(new PayCheckItem("2020.01.19", "3,200"));

        list_pay.setAdapter(payCheckAdapter);

        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

}
