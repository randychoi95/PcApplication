package com.ezen.MyPcApplication.Side_Navigation.Reservation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ezen.MyPcApplication.First_View.JoinItem;
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

    String myPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_reservation_check, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        list_reservation = rootView.findViewById(R.id.list_reservation);

        // 레이아웃 인플레이터를 이용하여, 뷰를 리사이클러뷰에 바로 제공
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list_reservation.setLayoutManager(layoutManager);

        // 어플 ID 가져오기
        String email = currentUser.getEmail();

        // 현재 어플 사용자의 핸드폰 번호 가져오기
        db.collection("Member").whereEqualTo("id", email).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot snapshot : queryDocumentSnapshots){
                    JoinItem joinItem = snapshot.toObject(JoinItem.class);
                    myPhone = joinItem.getPhone();

                    reservationCheckAdapter = new ReservationCheckAdapter();
                    // 현재 어플 사용자의 핸드폰 번호를 이용하여 현재 어플 사용자의 예약내역 가져오기
                    db.collection("Reservation").whereEqualTo("phone", myPhone).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                                Reservation_CheckDTO reservationCheckDTO = snapshot.toObject(Reservation_CheckDTO.class);
                                reservationCheckAdapter.addItem(new Reservation_CheckDTO(reservationCheckDTO.getSeat(), reservationCheckDTO.getPcname(), reservationCheckDTO.getDate()));
                            }
                            list_reservation.setAdapter(reservationCheckAdapter);
                        }
                    });
                    return;
                }
            }
        });
        // 아답터 클래스 생성

        return rootView;
    }

}
