package com.ezen.MyPcApplication.Side_Navigation.Reservation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ezen.MyPcApplication.R;

import java.util.ArrayList;

public class ReservationCheckAdapter extends RecyclerView.Adapter<ReservationCheckAdapter.ViewHolder> {

    ArrayList<Reservation_CheckDTO> items = new ArrayList<Reservation_CheckDTO>();
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
        Reservation_CheckDTO item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Reservation_CheckDTO item){
        items.add(item);
    }

    public void Reverse(){
//        Collections.reverse(items);
        Log.e("size", Integer.toString(items.size()));
        for(int i=0; i<items.size(); i++) {
            Log.e("date", items.get(i).getDate());
            Log.e("seat", items.get(i).getSeat());
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView date_content;
        TextView pcname_content;
        TextView reservation_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date_content = itemView.findViewById(R.id.date_content);
            pcname_content = itemView.findViewById(R.id.pcname_content);
            reservation_content = itemView.findViewById(R.id.reservation_content);
        }

        public void setItem(Reservation_CheckDTO item){

            date_content.setText(item.getDate());
            pcname_content.setText(item.getPcname());
            reservation_content.setText(item.getSeat());
        }
    }

}
