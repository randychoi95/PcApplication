package com.ezen.MyPcApplication.After_Main.Home_Tap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ezen.MyPcApplication.After_Main.MainActivity;
import com.ezen.MyPcApplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;


public class HomeFragment extends Fragment implements Home_Pager_Adapter.setClickListener {

    MainActivity mainActivity;

    Home_Pager_Adapter adapter;
    ViewPager viewPager;
    PageIndicatorView pageIndicatorView;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    FirebaseUser user;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // LayoutInflator 클래스 : XML 뷰파일을 실제 뷰객체로 만들어 주는 시스템 함수
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        user = firebaseAuth.getCurrentUser();


        // 아까 만든 viewPager
        viewPager = (ViewPager)rootView.findViewById(R.id.pager);
        // adapter 초기화
        adapter = new Home_Pager_Adapter(getContext(), this);
        // adapter 연결
        viewPager.setAdapter(adapter);

        // pageIndicatorView 적용
        pageIndicatorView = rootView.findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setCount(3);
        pageIndicatorView.setSelection(1);
        pageIndicatorView.setAnimationType(AnimationType.SLIDE);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {/*empty*/}

            @Override
            public void onPageSelected(int position) {
                pageIndicatorView.setSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {/*empty*/}
        });

        return rootView;
    }

    @Override
    public void setClick(Intent intent) {
        startActivity(intent);
    }

}
