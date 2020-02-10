package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ezen.MyPcApplication.R;
import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Reservation.ReservationActivity;

public class Pc_Info_TabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_pc_info_tab, container, false);

        Button btn_reservation = rootView.findViewById(R.id.btn_reservation);
        btn_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ReservationActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
}
