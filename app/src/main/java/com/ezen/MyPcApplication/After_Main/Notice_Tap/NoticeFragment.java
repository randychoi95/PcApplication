package com.ezen.MyPcApplication.After_Main.Notice_Tap;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ezen.MyPcApplication.R;

public class NoticeFragment extends Fragment implements NoticeAdapter.setClickListener {

    RecyclerView list_notice;
    NoticeAdapter noticeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_notice, container, false);

        list_notice = rootView.findViewById(R.id.list_notice);

        // 레이아웃 인플레이터를 이용하여, 뷰를 리사이클러뷰에 바로 제공
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list_notice.setLayoutManager(layoutManager);

        // 아답터 클래스 생성
        noticeAdapter = new NoticeAdapter(this);
        noticeAdapter.addItem(new Noticeitem("공지사항 제목1", "공지사항 내용1"));
        noticeAdapter.addItem(new Noticeitem("공지사항 제목2", "공지사항 내용2"));
        noticeAdapter.addItem(new Noticeitem("공지사항 제목3", "공지사항 내용3"));

        list_notice.setAdapter(noticeAdapter);

        return rootView;

    }


    @Override
    public void setClick(Intent intent) {
        startActivity(intent);
    }

}
