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

import com.admin.foodlabrinthadmin.Handler.ReviewsAdapter;
import com.admin.foodlabrinthadmin.Handler.UserDisplayAdapter;
import com.admin.foodlabrinthadmin.Model.Reviews;
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
public class ReviewsDisplayFragment extends Fragment {


    public ReviewsDisplayFragment() {
        // Required empty public constructor
    }

    private DatabaseReference reference;
    private RecyclerView reviewRecycler;
    private ArrayList<Reviews> reviewsArrayList;
    private ReviewsAdapter reviewsAdapter;


    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_reviews_display, container, false);
        reviewRecycler = (RecyclerView) view.findViewById(R.id.reviewRecycler);
        reviewRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        reviewsArrayList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference().child("reviews");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Reviews users = dataSnapshot1.getValue(Reviews.class);
                    reviewsArrayList.add(users);
                }
                reviewsAdapter = new ReviewsAdapter(getActivity(), reviewsArrayList);
                reviewRecycler.setAdapter(reviewsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Something went wrong. Please restart the app", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
