package com.ezen.MyPcApplication.After_Main.Find_Store_Tap;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.Pc_Info_TabActivity;
import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Review.Pc_Review_TabFragment;
import com.ezen.MyPcApplication.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// 제네릭으로 타입을 추가함.
public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    setClickListener listener;

    ArrayList<StoreItem> items = new ArrayList<StoreItem>();

    View itemView;

    StoreAdapter(setClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        itemView = inflater.inflate(R.layout.activity_pc_list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        StoreItem item = items.get(position);
        holder.setItem(item);




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), Pc_Info_TabActivity.class);
                // Pc_Info_TabActivity에 데이터 보내주기
                intent.putExtra("name", items.get(position).getName());
                intent.putExtra("address", items.get(position).getAddress());
                intent.putExtra("cpu", items.get(position).getCpu());
                intent.putExtra("ram", items.get(position).getRam());
                intent.putExtra("vga", items.get(position).getVga());
                listener.setClick(intent);
                
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(StoreItem item) {
        items.add((item));
    }

    // 리스트 거리순으로 정렬
    public void sort(){
        Comparator<StoreItem> cmpAsc = new Comparator<StoreItem>() {
            @Override
            public int compare(StoreItem item1, StoreItem item2) {
                Location locationMy = new Location("LocationMy");
                locationMy.setLatitude(37.655878); // 위도
                locationMy.setLongitude(127.062434); // 경도

                Location locationDes1 = new Location("locationDes");
                locationDes1.setLatitude(Double.parseDouble(item1.getLatitude())); // 위도
                locationDes1.setLongitude(Double.parseDouble(item1.getLongitude())); // 경도

                Location locationDes2 = new Location("locationDes");
                locationDes2.setLatitude(Double.parseDouble(item2.getLatitude())); // 위도
                locationDes2.setLongitude(Double.parseDouble(item2.getLongitude())); // 경도

                double distance1 = locationMy.distanceTo(locationDes1);
                double distance2 = locationMy.distanceTo(locationDes2);

                if (distance1 == distance2)
                    return 0;
                else if (distance1 > distance2)
                    return 1;
                else
                    return -1;
            }
        } ;
        Collections.sort(items, cmpAsc);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView pc_name;
        TextView pc_street;
        TextView pc_address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pc_name = itemView.findViewById(R.id.pc_name);
            pc_street = itemView.findViewById(R.id.pc_street);
            pc_address = itemView.findViewById(R.id.pc_address);
        }

        public void setItem(StoreItem item){
            Location locationMy = new Location("locationMy");
            locationMy.setLatitude(37.655878); // 위도
            locationMy.setLongitude(127.062434); // 경도

            Location locationDes = new Location("locationDes");
            locationDes.setLatitude(Double.parseDouble(item.getLatitude())); // 위도
            locationDes.setLongitude(Double.parseDouble(item.getLongitude())); // 경도

            double distance = locationMy.distanceTo(locationDes);

            pc_name.setText(item.getName());
            pc_street.setText(Double.toString(Math.round(distance) / 1000.0) + "km");
            pc_address.setText(item.getAddress());
        }
    }

    interface setClickListener{
        void setClick(Intent intent);
    }

}
