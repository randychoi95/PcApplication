package com.ezen.MyPcApplication.After_Main.Notice_Tap;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ezen.MyPcApplication.R;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    setClickListener listener;

    ArrayList<Noticeitem> items = new ArrayList<Noticeitem>();
    View itemView;

    Noticeitem item;

    NoticeAdapter(setClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoticeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        itemView = inflater.inflate(R.layout.activity_notice_item, parent, false);
        return new NoticeAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeAdapter.ViewHolder holder, final int position) {
        item = items.get(position);
        holder.setItem(item);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), Notice_ContentActivity.class);
                intent.putExtra("title", items.get( position ).getTitle());
                intent.putExtra("content", items.get( position ).getContent());
                listener.setClick(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Noticeitem item){
        items.add((item));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView notice_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            notice_title = itemView.findViewById(R.id.text_title);
        }

        public void setItem(Noticeitem item){

            notice_title.setText(item.getTitle());
        }
    }

    interface setClickListener{
        void setClick(Intent intent);
    }
}
