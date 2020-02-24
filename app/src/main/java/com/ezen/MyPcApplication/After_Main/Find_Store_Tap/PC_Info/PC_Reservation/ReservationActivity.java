package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Reservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ezen.MyPcApplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReservationActivity extends AppCompatActivity {

    int pc_reservation_seat;

    // pc방 좌석
    Button[] btnArrs = new Button[27];
    int[] btnid = {R.id.seat_1, R.id.seat_2, R.id.seat_3, R.id.seat_4, R.id.seat_5, R.id.seat_6, R.id.seat_7, R.id.seat_8,
                    R.id.seat_9, R.id.seat_10, R.id.seat_11, R.id.seat_12, R.id.seat_13, R.id.seat_14, R.id.seat_15, R.id.seat_16,
                    R.id.seat_17, R.id.seat_18, R.id.seat_19, R.id.seat_20, R.id.seat_21, R.id.seat_22, R.id.seat_23, R.id.seat_24,
                    R.id.seat_25, R.id.seat_26, R.id.seat_27};
    Button btn_num;

    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;
    FirebaseFirestore db;

    ColorDrawable color;

    String pcname;
    String id;
    int isEmpty;
    String seat;
    String myuid; // 내 계정에서 나의 Pc방 id로 로그인한 사람 uid
    String otheruid; // 내 계정에서 다른 사람의 Pc방 id로 로그인한 사람 uid

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        // 툴바에 뒤로가기 백버튼 생성
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // pc방 이름과 pc방 회원 id가져오기
        Intent intent = getIntent();
        pcname = intent.getStringExtra("pcname");
        id = intent.getStringExtra("id");
        myuid = intent.getStringExtra("uid");

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        // 생성 시 피시방 회원 테이블 가져오기
        db.collection("Reservation").whereEqualTo("pcname", pcname).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot snapshot : queryDocumentSnapshots){
                    ReservationDTO reservationDTO = snapshot.toObject(ReservationDTO.class);
                    if(id.equals(reservationDTO.getId())){
                        isEmpty = reservationDTO.getIsEmpty();
                        seat = reservationDTO.getSeat();
                        otheruid = reservationDTO.getUid();
                        doFirst(isEmpty, seat);
                        return;
                    }
                }
            }
        });

        for(int i=0; i<btnArrs.length; i++){
            final int index;
            index = i;
            btnArrs[index] = findViewById(btnid[index]);
            btnArrs[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color = (ColorDrawable) btnArrs[index].getBackground();
                    int bgColor = color.getColor();
                    // 예약가능한 자리인지
                    if (bgColor == Color.argb(255, 102, 153, 0)) {
                        btn_num = btnArrs[index];
                        doDialog(index+1);
                    }else {
                        Toast.makeText(getApplicationContext(), "예약 가능한 자리가 아닙니다", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }// onCreate

    // 초기 자리 상태( 0:예약 불가능, 1:예약 대기, 2:예약 가능)
    private void doFirst(int isEmpty, String seat){
        for(int i=0; i<btnArrs.length; i++) {
            if( Integer.parseInt(seat) == (i+1) ) {
                if (isEmpty == 0) {
                    btnArrs[i].setBackgroundColor(Color.argb(255, 204, 0, 0));
                    pc_reservation_seat = i + 1;
                } else if (isEmpty == 1) {
                    btnArrs[i].setBackgroundColor(Color.rgb(230, 230, 230));
                    pc_reservation_seat = i + 1;
                } else if (isEmpty == 2) {
                    btnArrs[i].setBackgroundColor(Color.argb(255, 102, 153, 0));
                    pc_reservation_seat = i + 1;
                }
                break;
            }
        }
    }// doFirst

    // 예약확인 다이얼로그
    private void doDialog(final int seat_num){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("예약 확인").setMessage("선택하신 자리로 예약을 하시겠습니까?");

                builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(pc_reservation_seat != 0) {
                            reset(btnArrs[pc_reservation_seat - 1]);
                            pc_reservation_seat = seat_num;
                            doReservation();
                            btn_num.setBackgroundColor(Color.rgb(230, 230, 230));
                        } else {
                            pc_reservation_seat = seat_num;
                            doReservation();
                            btn_num.setBackgroundColor(Color.rgb(230, 230, 230));
                        }
                    }
                });

                builder.setPositiveButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

                final AlertDialog alertDialog = builder.create();
                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                    }
                });
                alertDialog.show();
    }// doDialog

    // 예약자리를 옮길 경우 리셋
    private void reset(Button btn_num){
        btn_num.setBackgroundColor(Color.argb(255, 102, 153, 0));
    }


    // 예약하기
    private void doReservation(){
        String current_uid = currentUser.getUid();

            if (myuid.equals(current_uid)) {
                doDelete();
                try {
                    Thread.sleep(1000);
                    doAdd();
                    return;
                } catch (Exception e) {
                }
            }
            if(otheruid != null) {
                if (otheruid.equals(myuid)) {
                    doDelete();
                    try {
                        Thread.sleep(1000);
                        doAdd();
                        return;
                    } catch (Exception e) {
                    }
                }
            }else {
                doAdd();
            }
    }// doReservation

    // db에 예약정보 저장
    private void doAdd(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

        ReservationDTO reservationDTO = new ReservationDTO(pcname, id, Integer.toString(pc_reservation_seat),
                sdf.format(cal.getTime()), 1, myuid);

        db.collection("Reservation")
                .add( reservationDTO )
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(getApplicationContext(), "예약이 되었습니다", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "예약에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
    }// doAdd

    // 이전 예약정보 삭제
    private void doDelete(){
        db.collection("Reservation").whereEqualTo("pcname", pcname).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot snapshot : queryDocumentSnapshots){
                    ReservationDTO reservationDTO = snapshot.toObject(ReservationDTO.class);
                    if(id.equals(reservationDTO.getId())){
                        String documentID = snapshot.getId();
                        db.collection("Reservation").document(documentID).delete();
                        Log.e("delete", "삭제 성공");
                        return;
                    }
                }
            }
        });
    }// doDelete

    // 뒤로가기 버튼 누를 시에 이벤트 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
