package com.admin.foodlabrinthadmin.NavBarPages;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.admin.foodlabrinthadmin.Handler.UserDisplayAdapter;
import com.admin.foodlabrinthadmin.Model.Users;
import com.admin.foodlabrinthadmin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersDisplayFragment extends Fragment {


    public UsersDisplayFragment() {
        // Required empty public constructor
    }

    DatabaseReference reference;
    RecyclerView userRecycler;
    ArrayList<Users> usersArrayList;
    UserDisplayAdapter userDisplayAdapter;

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_users_display, container, false);
        userRecycler = (RecyclerView) view.findViewById(R.id.userRecycler);
        userRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        usersArrayList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference().child("user");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Users users = dataSnapshot1.getValue(Users.class);
                    usersArrayList.add(users);
                }
                userDisplayAdapter = new UserDisplayAdapter(getActivity(), usersArrayList);
                userRecycler.setAdapter(userDisplayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Something went wrong. Please restart the app", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

}
