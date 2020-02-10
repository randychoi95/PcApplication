package com.ezen.MyPcApplication.After_Main;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.ezen.MyPcApplication.FragmentCallback;
import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.StoreFragment;
import com.ezen.MyPcApplication.After_Main.Home_Tap.HomeFragment;
import com.ezen.MyPcApplication.After_Main.Notice_Tap.NoticeFragment;
import com.ezen.MyPcApplication.R;
import com.ezen.MyPcApplication.Side_Navigation.Login.Login_CheckFragment;
import com.ezen.MyPcApplication.Side_Navigation.Pay.Pay_CheckFragment;
import com.ezen.MyPcApplication.Side_Navigation.Person.Person_InfoFragment;
import com.ezen.MyPcApplication.Side_Navigation.Reservation.Reservation_CheckFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentCallback {

    HomeFragment fragment_home;
    StoreFragment storeFragment;
    NoticeFragment noticeFragment;
    Toolbar toolbar;
    DrawerLayout drawer;

    Person_InfoFragment person_infoFragment;
    Login_CheckFragment login_checkFragment;
    Pay_CheckFragment pay_checkFragment;
    Reservation_CheckFragment reservation_checkFragment;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.ConstraintLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragment_home = new HomeFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment_home).commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.tab1:
                        fragment_home = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_home).commit();
                        return true; // 탭 변경 허용
                    case R.id.tab2:
                        storeFragment = new StoreFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, storeFragment).commit();
                        return true;
                    case R.id.tab3:
                        noticeFragment = new NoticeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, noticeFragment).commit();
                        return true;
                }

                return false; // 탭 변경 취소
            }
        });
    }

    @Override
    public void onBackPressed() { // 하드웨어 백버튼 눌렀을 때 처리
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if(id == R.id.nav_info){
            Toast.makeText(this, "개인정보 메뉴가 선택됨", Toast.LENGTH_SHORT).show();
            onFragmentSelected(0, null);
        } else if(id == R.id.nav_login){
            Toast.makeText(this, "로그인 내역 메뉴가 선택됨", Toast.LENGTH_SHORT).show();
            onFragmentSelected(1, null);
        }else if(id == R.id.nav_pay){
            Toast.makeText(this, "결재 내역 메뉴가 선택됨", Toast.LENGTH_SHORT).show();
            onFragmentSelected(2, null);
        }else if(id == R.id.nav_reservation){
            Toast.makeText(this, "예약 내역 메뉴가 선택됨", Toast.LENGTH_SHORT).show();
            onFragmentSelected(3, null);
        }

        drawer.closeDrawer(GravityCompat.START);

        return false;
    }

    @Override
    public void onFragmentSelected(int position, Bundle bundle) {

        person_infoFragment = new Person_InfoFragment();
        login_checkFragment = new Login_CheckFragment();
        pay_checkFragment = new Pay_CheckFragment();
        reservation_checkFragment = new Reservation_CheckFragment();

        Fragment curFragment = null;
        if (position == 0) {
            curFragment = person_infoFragment;
        } else if (position == 1) {
            curFragment = login_checkFragment;
        }else if (position == 2) {
            curFragment = pay_checkFragment;
        }else if (position == 3) {
            curFragment = reservation_checkFragment;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, curFragment).commit();
    }

    public void changeTap(int index){
        if(index == 0)
            bottomNavigationView.setSelectedItemId(R.id.tab1);
        else if(index == 1)
            bottomNavigationView.setSelectedItemId(R.id.tab2);
        else
            bottomNavigationView.setSelectedItemId(R.id.tab3);
    }
}
