package com.ezen.MyPcApplication.Side_Navigation.Reservation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.PC_Reservation.ReservationDTO;
import com.ezen.MyPcApplication.R;

import java.util.ArrayList;
import java.util.Collections;

public class ReservationCheckAdapter extends RecyclerView.Adapter<ReservationCheckAdapter.ViewHolder> {

    ArrayList<ReservationDTO> items = new ArrayList<ReservationDTO>();
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
        ReservationDTO item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(ReservationDTO item){
        items.add(item);
    }

    public void Reverse(){
        Collections.reverse(items);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView date_content;
        TextView reservation_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date_content = itemView.findViewById(R.id.date_content);
            reservation_content = itemView.findViewById(R.id.reservation_content);
        }

        public void setItem(ReservationDTO item){

            date_content.setText(item.getDate());
            reservation_content.setText(item.getSeat());
        }
    }

}
