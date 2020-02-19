package com.ezen.MyPcApplication.Side_Navigation.Person;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.ezen.MyPcApplication.After_Main.MainActivity;
import com.ezen.MyPcApplication.First_View.FirstActivity;
import com.ezen.MyPcApplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class PersonInfoAdapter extends RecyclerView.Adapter<PersonInfoAdapter.ViewHolder> {
    setClickListener listener;

    ArrayList<PersonInfoItem>
            items = new ArrayList<PersonInfoItem>();
    View itemView;

    FirebaseAuth auth = FirebaseAuth.getInstance();  // 파이어베이스 인증
    FirebaseUser currentUser = auth.getCurrentUser(); // 현재 사용자의 계정 데이타

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
                    currentUser.delete();
                    Intent intent = new Intent(itemView.getContext(), FirstActivity.class);
                    listener.setClick(intent);

                    Toast.makeText(v.getContext(), position + "회원 탈퇴 되었습니다.", Toast.LENGTH_SHORT).show();
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
