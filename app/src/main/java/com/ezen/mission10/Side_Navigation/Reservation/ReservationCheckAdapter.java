package com.ezen.mission10.Side_Navigation.Reservation;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ezen.mission10.R;
import com.ezen.mission10.Side_Navigation.Pay.PayCheckItem;

import java.util.ArrayList;

public class ReservationCheckAdapter extends RecyclerView.Adapter<ReservationCheckAdapter.ViewHolder> {

    ArrayList<ReservationCheckItem> items = new ArrayList<ReservationCheckItem>();
    View itemView;

    @NonNull
    @Override
    public ReservationCheckAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            itemView = inflater.inflate(R.layout.activity_reservation_list_item, parent, false);

            return new ReservationCheckAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationCheckAdapter.ViewHolder holder, final int position) {
            ReservationCheckItem item = items.get(position);
            holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(ReservationCheckItem item){
        items.add(item);
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView date_content;
        TextView reservation_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date_content = itemView.findViewById(R.id.date_content);
            reservation_content = itemView.findViewById(R.id.reservation_content);
        }

        public void setItem(ReservationCheckItem item){

            date_content.setText(item.getLoginDate());
            reservation_content.setText(item.getReservation_content());
        }
    }

}
