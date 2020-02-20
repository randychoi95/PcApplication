package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Member.PcRoomLoginActivity;
import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Review.Pc_Review_TabFragment;
import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.StoreFragment;
import com.ezen.MyPcApplication.After_Main.Home_Tap.HomeFragment;
import com.ezen.MyPcApplication.After_Main.MainActivity;
import com.ezen.MyPcApplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Pc_Info_TabActivity extends AppCompatActivity {

    Pc_Info_TabFragment pc_info_tab;
    Pc_Review_TabFragment pc_review_tabFragment;
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_info_tab);

        pc_info_tab = new Pc_Info_TabFragment();
        pc_review_tabFragment = new Pc_Review_TabFragment();

        // 툴바에 뒤로가기 백버튼 생성
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabs = (TabLayout)findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("PC방 정보"));
        tabs.addTab(tabs.newTab().setText("리뷰"));

        // 리스트에서 데이터 받기
        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        String address = intent.getExtras().getString("address");
        String cpu = intent.getExtras().getString("cpu");
        String ram = intent.getExtras().getString("ram");
        String vga = intent.getExtras().getString("vga");

        // 프레그먼트에 데이터 보내주기
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("address", address);
        bundle.putString("cpu", cpu);
        bundle.putString("ram", ram);
        bundle.putString("vga", vga);
        pc_info_tab.setArguments(bundle);

        Bundle bundle2 = new Bundle();
        bundle2.putString("name", name);
        pc_review_tabFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, pc_info_tab).commit();


        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, pc_info_tab).commit();

                } else if(tab.getPosition() == 1){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, pc_review_tabFragment).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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
