package com.ezen.MyPcApplication.Side_Navigation.Person;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ezen.MyPcApplication.First_View.FirstActivity;
import com.ezen.MyPcApplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PersonInfoAdapter extends RecyclerView.Adapter<PersonInfoAdapter.ViewHolder> {
    setClickListener listener;

    ArrayList<PersonInfoItem> items = new ArrayList<PersonInfoItem>();
    View itemView;

    FirebaseAuth auth = FirebaseAuth.getInstance();  // 파이어베이스 인증
    FirebaseUser currentUser = auth.getCurrentUser(); // 현재 사용자의 계정 데이타
    FirebaseFirestore db;

    Person_InfoFragment person_infoFragment;

    public PersonInfoAdapter(Activity activity) {

    }

    public PersonInfoAdapter(Person_InfoFragment fragment,  setClickListener listener){
        this.person_infoFragment = fragment;
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
                    showDialog();
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

    // 회원탈퇴 다이얼로그
    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(person_infoFragment.getContext());

        builder.setTitle("회원 탈퇴").setMessage("정말 회원탈퇴 하시겠습니까 ?");

        builder.setNegativeButton("예", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // firebase 회원탈퇴
                currentUser.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.e("Han", "계정삭제됨.");
                                }
                            }
                        });
                auth.signOut();

                doDelete();

                try{
                    Thread.sleep(2000);
                    Intent intent = new Intent(itemView.getContext(), FirstActivity.class);
                    listener.setClick(intent);

                    Toast.makeText(person_infoFragment.getContext(), "회원 탈퇴 되었습니다.", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        builder.setPositiveButton("아니요", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(person_infoFragment.getContext(), "취소되었습니다.", Toast.LENGTH_SHORT).show();

            }
        });


        final AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
            @Override public void onShow(DialogInterface arg0) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK); } });

        alertDialog.show();
    }

    // 회원탈퇴 메소드
    private void doDelete(){
        db = FirebaseFirestore.getInstance();

        db.collection("Member").whereEqualTo("uid", currentUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot snapshot : queryDocumentSnapshots){
                        String documentID = snapshot.getId();
                        db.collection("Member").document(documentID).delete();
                        Log.e("delete", "삭제 성공");
                        return;
                }
            }
        });
    }


}
