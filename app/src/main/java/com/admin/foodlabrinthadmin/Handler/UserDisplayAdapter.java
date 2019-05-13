package com.admin.foodlabrinthadmin.Handler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.foodlabrinthadmin.Model.Users;
import com.admin.foodlabrinthadmin.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserDisplayAdapter extends RecyclerView.Adapter<UserDisplayAdapter.UserVH> {

    Context context;
    ArrayList<Users> usersArrayList;

    public UserDisplayAdapter(Context context, ArrayList<Users> usersArrayList) {
        this.context = context;
        this.usersArrayList = usersArrayList;
    }

    @NonNull
    @Override
    public UserDisplayAdapter.UserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserVH(LayoutInflater.from(context).inflate(R.layout.user_cell, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserDisplayAdapter.UserVH holder, int position) {
        holder.name.setText(usersArrayList.get(position).getPassword());
        holder.email.setText(usersArrayList.get(position).getEmail());
        holder.password.setText(usersArrayList.get(position).getFullname());
    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    public class UserVH extends RecyclerView.ViewHolder {

        TextView name, email, password;
        ImageButton delete;
        public UserVH(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.res_title);
            email = (TextView) itemView.findViewById(R.id.res_cuisine);
            password = (TextView) itemView.findViewById(R.id.res_location);
            delete = (ImageButton) itemView.findViewById(R.id.deleteUser);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference driverRef = FirebaseDatabase.getInstance().getReference().child("user");
                    driverRef.removeValue();
                }
            });
        }
    }
}
