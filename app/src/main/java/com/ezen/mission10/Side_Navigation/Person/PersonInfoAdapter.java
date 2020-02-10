package com.ezen.mission10.Side_Navigation.Person;

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

public class PersonInfoAdapter extends RecyclerView.Adapter<PersonInfoAdapter.ViewHolder> {
    setClickListener listener;

    ArrayList<PersonInfoItem> items = new ArrayList<PersonInfoItem>();
    View itemView;

    PersonInfoAdapter(setClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public PersonInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        itemView = inflater.inflate(R.layout.activity_person_list_item, parent, false);

        return new PersonInfoAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonInfoAdapter.ViewHolder holder, final int position) {
        PersonInfoItem item = items.get(position);
        holder.setItem(item);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == 0) {
                    Intent intent = new Intent(itemView.getContext(), ConvertPwActivity.class);
                    listener.setClick(intent);
                    Toast.makeText(v.getContext(), position + "번째", Toast.LENGTH_SHORT).show();
                } else if(position == 1) {
                    // 회원탈퇴
                    Toast.makeText(v.getContext(), position + "번째", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(PersonInfoItem item){
        items.add(item);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewKind;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewKind = itemView.findViewById(R.id.textViewKind);
        }

        public void setItem(PersonInfoItem item){
            textViewKind.setText(item.getKind());
        }
    }

    interface setClickListener{
        void setClick(Intent intent);
    }
}
