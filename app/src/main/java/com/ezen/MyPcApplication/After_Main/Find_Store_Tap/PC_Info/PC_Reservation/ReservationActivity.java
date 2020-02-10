package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.StoreFragment;
import com.ezen.MyPcApplication.R;

public class ReservationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        // 툴바에 뒤로가기 백버튼 생성
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

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
