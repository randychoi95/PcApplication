package com.ezen.mission10.After_Main.Find_Store_Tap.PC_Info;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ezen.mission10.After_Main.Find_Store_Tap.PC_Review.Pc_Review_TabFragment;
import com.ezen.mission10.R;
import com.google.android.material.tabs.TabLayout;

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

        tabs = (TabLayout)findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("PC방 정보"));
        tabs.addTab(tabs.newTab().setText("리뷰"));

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
}
