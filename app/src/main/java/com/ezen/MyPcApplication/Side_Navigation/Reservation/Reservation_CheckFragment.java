package com.ezen.MyPcApplication.Side_Navigation.Reservation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ezen.MyPcApplication.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Reservation_CheckFragment extends Fragment {
    RecyclerView list_reservation;
    ReservationCheckAdapter reservationCheckAdapter;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    FirebaseUser currentUser;

    String uid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_reservation_check, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        uid = currentUser.getUid();

        list_reservation = rootView.findViewById(R.id.list_reservation);

        // 레이아웃 인플레이터를 이용하여, 뷰를 리사이클러뷰에 바로 제공
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list_reservation.setLayoutManager(layoutManager);

        // 아답터 클래스 생성
        reservationCheckAdapter = new ReservationCheckAdapter();
        db.collection("Reservation").whereEqualTo("uid", uid).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot snapshot : queryDocumentSnapshots){
                    Reservation_CheckDTO reservationCheckDTO = snapshot.toObject(Reservation_CheckDTO.class);
                    reservationCheckAdapter.addItem(new Reservation_CheckDTO(reservationCheckDTO.getSeat(), reservationCheckDTO.getPcname(), reservationCheckDTO.getDate()));
                }
                reservationCheckAdapter.Reverse();
                list_reservation.setAdapter(reservationCheckAdapter);
            }
        });
//        Log.e("items.seat", reservationCheckAdapter.items.get(0).getSeat());


        return rootView;
    }

}
