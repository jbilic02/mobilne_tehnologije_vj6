package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<UserData> userData;
    public UserAdapter (Context c, ArrayList<UserData> userData){
        this.context = c;
        this.userData = userData;
    }

    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    //Ova metoda interno poziva na ažuriranje RecyclerView.ViewHolder sadržaja stavkom na zadanom položaju
    //RecyclerView poziva da podatke prikaže na navedenom položaju
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserData userData = this.userData.get(position);

        holder.tvInt.setText(userData.getGodina());
        holder.tvName.setText(userData.getIme());
        holder.tvPredavac.setText(userData.getPredavac());


        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), updateData.class);
                i.putExtra("id",userData.getId());
                i.putExtra("godina",userData.getGodina());
                i.putExtra("ime",userData.getIme());
                i.putExtra("predavac",userData.getPredavac());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    //Vraća ukupan broj stavki u skupu podataka koje drži adapter
    public int getItemCount() {
        return userData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvInt,tvName,tvPredavac;
        Button btnUpdate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInt = itemView.findViewById(R.id.tvInt);
            tvName = itemView.findViewById(R.id.tvName);
            tvPredavac = itemView.findViewById(R.id.tvPredavac);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }
    }
}
