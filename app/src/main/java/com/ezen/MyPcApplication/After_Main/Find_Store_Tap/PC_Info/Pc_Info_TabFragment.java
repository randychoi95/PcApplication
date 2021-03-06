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

import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Member.PcRoomLoginActivity;
import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.StoreItem;
import com.ezen.MyPcApplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.FirebaseFirestore;


public class Pc_Info_TabFragment extends Fragment
        implements OnMapReadyCallback {
    String name;
    String address;
    String cpu;
    String ram;
    String vga;
    String latitude;
    String longitude;


    FirebaseFirestore db;

    StoreItem storeItem;

    private MapView mapView = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_pc_info_tab, container, false);

        mapView = (MapView)rootView.findViewById(R.id.map);
        mapView.getMapAsync(this);

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
            latitude = extra.getString("latitude");
            longitude = extra.getString("longitude");
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

        // 예약하기 버튼
        Button btn_reservation = rootView.findViewById(R.id.btn_reservation);
        btn_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PcRoomLoginActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //액티비티가 처음 생성될 때 실행되는 함수
        if(mapView != null)
        {
            mapView.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng SEOUL = new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL); // 마커 위치
        markerOptions.title(name); // 마커 타이틀

        googleMap.addMarker(markerOptions); // 맵에 마커 추가
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL)); // 구글맵 카메라 위치
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(18)); // 구글맵 줌
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
