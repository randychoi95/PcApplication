package com.ezen.mission10.Side_Navigation.Pay;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ezen.mission10.R;
import com.ezen.mission10.Side_Navigation.Login.LoginCheckItem;

import java.util.ArrayList;

public class PayCheckAdapter extends RecyclerView.Adapter<PayCheckAdapter.ViewHolder> {
    setClickListener listener;

    ArrayList<PayCheckItem> items = new ArrayList<PayCheckItem>();
    View itemView;

    @NonNull
    @Override
    public PayCheckAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            itemView = inflater.inflate(R.layout.activity_pay_list_item, parent, false);

            return new PayCheckAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PayCheckAdapter.ViewHolder holder, final int position) {
            PayCheckItem item = items.get(position);
            holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(PayCheckItem item){
        items.add(item);
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView date_content;
        TextView pay_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date_content = itemView.findViewById(R.id.date_content);
            pay_content = itemView.findViewById(R.id.pay_content);
        }

        public void setItem(PayCheckItem item){

            date_content.setText(item.getLoginDate());
            pay_content.setText(item.getPay_content());
        }
    }

    interface setClickListener{
        void setClick(Intent intent);
    }

}
