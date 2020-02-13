package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.StoreItem;
import com.ezen.MyPcApplication.R;
import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Reservation.ReservationActivity;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Pc_Info_TabFragment extends Fragment {
    String name;
    String address;
    String cpu;
    String ram;
    String vga;

    FirebaseFirestore db;

    StoreItem storeItem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_pc_info_tab, container, false);

        db = FirebaseFirestore.getInstance();

        storeItem = new StoreItem();

        // 액티비티에서 데이터 받기
        Bundle extra = this.getArguments();
        if(extra != null) {
            extra = getArguments();
            name = extra.getString("name");
            address = extra.getString("address");
            cpu = extra.getString("cpu");
            ram = extra.getString("ram");
            vga = extra.getString("vga");
        }

        TextView pc_name = rootView.findViewById(R.id.pc_name);
        pc_name.setText(name);

        TextView pc_address = rootView.findViewById(R.id.pc_address);
        pc_address.setText(address);

        TextView pc_cpu_info = rootView.findViewById(R.id.pc_cpu_info);
        pc_cpu_info.setText(cpu);

        TextView pc_ram_info = rootView.findViewById(R.id.pc_ram_info);
        pc_ram_info.setText(ram);

        TextView pc_vga_info = rootView.findViewById(R.id.pc_vga_info);
        pc_vga_info.setText(vga);

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
