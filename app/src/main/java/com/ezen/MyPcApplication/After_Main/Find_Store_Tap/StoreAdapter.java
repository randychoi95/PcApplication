package com.ezen.MyPcApplication.After_Main.Find_Store_Tap;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ezen.MyPcApplication.After_Main.Find_Store_Tap.PC_Info.Pc_Info_TabActivity;
import com.ezen.MyPcApplication.R;

import java.util.ArrayList;

// 제네릭으로 타입을 추가함.
public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    setClickListener listener;

    ArrayList<StoreItem> items = new ArrayList<StoreItem>();
    View itemView;

    StoreAdapter(setClickListener listener){
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
                listener.setClick(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(StoreItem item){
        items.add((item));
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
            pc_name.setText(item.getName());
            pc_street.setText(item.getStreet());
            pc_address.setText(item.getAddress());
        }
    }

    interface setClickListener{
        void setClick(Intent intent);
    }

}
