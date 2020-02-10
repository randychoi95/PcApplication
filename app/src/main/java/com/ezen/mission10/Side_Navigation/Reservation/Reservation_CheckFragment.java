package com.ezen.mission10.Side_Navigation.Reservation;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ezen.mission10.R;
import com.ezen.mission10.Side_Navigation.Pay.PayCheckAdapter;
import com.ezen.mission10.Side_Navigation.Pay.PayCheckItem;

public class Reservation_CheckFragment extends Fragment {

    RecyclerView list_reservation;
    ReservationCheckAdapter reservationCheckAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_reservation_check, container, false);

        list_reservation = rootView.findViewById(R.id.list_reservation);

        // 레이아웃 인플레이터를 이용하여, 뷰를 리사이클러뷰에 바로 제공
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list_reservation.setLayoutManager(layoutManager);

        // 아답터 클래스 생성
        reservationCheckAdapter = new ReservationCheckAdapter();
        reservationCheckAdapter.addItem(new ReservationCheckItem("2020.02.05", "24"));
        reservationCheckAdapter.addItem(new ReservationCheckItem("2020.02.05", "24"));
        reservationCheckAdapter.addItem(new ReservationCheckItem("2020.02.05", "24"));

        list_reservation.setAdapter(reservationCheckAdapter);

        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

}
