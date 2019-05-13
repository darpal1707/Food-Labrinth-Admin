package com.admin.foodlabrinthadmin.NavBarPages;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.admin.foodlabrinthadmin.Handler.PhotoDisplayAdapter;
import com.admin.foodlabrinthadmin.Model.Upload;
import com.admin.foodlabrinthadmin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotosDisplayFragment extends Fragment {


    public PhotosDisplayFragment() {
        // Required empty public constructor
    }

    private RecyclerView photos;
    private List<Upload> mUploads;
    private PhotoDisplayAdapter photoDisplayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_photos_display, container, false);

        photos = (RecyclerView) view.findViewById(R.id.photo_horizontal);
        mUploads = new ArrayList<>();

        DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("photos");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);
                    Log.e("photo url", String.valueOf(postSnapshot));
                }
                photoDisplayAdapter = new PhotoDisplayAdapter(getActivity(), mUploads);
                photos.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                photos.setAdapter(photoDisplayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
