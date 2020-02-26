package com.ezen.MyPcApplication.After_Main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.ezen.MyPcApplication.First_View.FirstActivity;
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
import com.google.firebase.auth.FirebaseAuth;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentCallback, AutoPermissionsListener {

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

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Permission
        AutoPermissions.Companion.loadAllPermissions(this, 100);

        // firebase
        firebaseAuth = FirebaseAuth.getInstance();

        // toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // drawer
        drawer = findViewById(R.id.ConstraintLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // 기본으로 홈탭설정
        fragment_home = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment_home).commit();

        // 바텀네비게이션
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

        // 네비게이션 뷰
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View nav_header_view = navigationView.getHeaderView(0);

        // 로그인한 유저표시
        TextView textUser = nav_header_view.findViewById(R.id.user_id);
        String user = firebaseAuth.getCurrentUser().getEmail();
        textUser.setText(user + "님 환영합니다.");

        // 로그아웃
        Button btn_logout = nav_header_view.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogout();
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
            onFragmentSelected(0, null);
        } else if(id == R.id.nav_login){
            onFragmentSelected(1, null);
        }else if(id == R.id.nav_pay){
            onFragmentSelected(2, null);
        }else if(id == R.id.nav_reservation){
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

    // 로그아웃 메소드
    public void doLogout(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Light_Dialog);

        alertDialog.setMessage("로그아웃을 하시겠습니까?")
            .setTitle("로그아웃")
            .setPositiveButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            })
            .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    firebaseAuth.signOut();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                }
            })
            .setCancelable(false)//백버튼으로 팝업창이 닫히지 않도록 한다.
            .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int requestCode, String[] permissions) {
        StringBuilder sb = new StringBuilder();
        for( String permission : permissions) {
            sb.append( permission );
            sb.append( "," );
        }
        Log.e("denied", sb.toString());
    }

    @Override
    public void onGranted(int requestCode, String[] permissions) {
        StringBuilder sb = new StringBuilder();
        for( String permission : permissions) {
            sb.append( permission );
            sb.append( "," );
        }
        Log.e("grandted", sb.toString());

    }

}
