package com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Review;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ezen.MyPcApplication.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

// 제네릭으로 타입을 추가함.
public class Pc_Review_Adapter extends RecyclerView.Adapter<Pc_Review_Adapter.ViewHolder> {

    ArrayList<Pc_Review_AdapterItem> items = new ArrayList<Pc_Review_AdapterItem>();
    View itemView;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        itemView = inflater.inflate(R.layout.activity_pc_review_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Pc_Review_AdapterItem item = items.get(position);
        holder.setItem(item);
    }

    // 리스트 최신날짜순으로 정렬
    public void Reverse(){
        Collections.reverse(items);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Pc_Review_AdapterItem item){
        items.add((item));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_userName;
        TextView text_date;
        TextView text_comments;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_userName = itemView.findViewById(R.id.text_userid);
            text_date = itemView.findViewById(R.id.text_date);
            text_comments = itemView.findViewById(R.id.text_comments);
            imageView = itemView.findViewById(R.id.user_image);
        }

        public void setItem(Pc_Review_AdapterItem item){
            text_userName.setText(item.getName());
            text_date.setText(item.getDate());
            text_comments.setText(item.getComments());
            imageView.setImageResource(item.getImageId());
        }
    }

}

