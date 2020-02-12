package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.StoreItem;
import com.ezen.MyPcApplication.R;
import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Reservation.ReservationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Pc_Info_TabFragment extends Fragment {
    String name;
    String address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_pc_info_tab, container, false);

        // 액티비티에서 데이터 받기
        Bundle extra = this.getArguments();
        if(extra != null) {
            extra = getArguments();
            name = extra.getString("name");
            address = extra.getString("address");
        }

        TextView pc_name = rootView.findViewById(R.id.pc_name);
        pc_name.setText(name);

        TextView pc_address = rootView.findViewById(R.id.pc_address);
        pc_address.setText(address);

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
