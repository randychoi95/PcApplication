package com.ezen.mission10.Side_Navigation.Login;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ezen.mission10.R;

import java.util.ArrayList;

public class LoginCheckAdapter extends RecyclerView.Adapter<LoginCheckAdapter.ViewHolder> {

    ArrayList<LoginCheckItem> items = new ArrayList<LoginCheckItem>();
    View itemView;

    @NonNull
    @Override
    public LoginCheckAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            itemView = inflater.inflate(R.layout.activity_login_list_item, parent, false);

            return new LoginCheckAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LoginCheckAdapter.ViewHolder holder, final int position) {
            LoginCheckItem item = items.get(position);
            holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(LoginCheckItem item){
        items.add(item);
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView date_content;
        TextView pcroom_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date_content = itemView.findViewById(R.id.date_content);
            pcroom_content = itemView.findViewById(R.id.PCroom_content);
        }

        public void setItem(LoginCheckItem item){

            date_content.setText(item.getLoginDate());
            pcroom_content.setText(item.getPcroomName());
        }
    }

}
