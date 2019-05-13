package com.admin.foodlabrinthadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.admin.foodlabrinthadmin.NavBarPages.PhotosDisplayFragment;
import com.admin.foodlabrinthadmin.NavBarPages.ReviewsDisplayFragment;
import com.admin.foodlabrinthadmin.NavBarPages.UsersDisplayFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public  static BottomNavigationView navigation;
    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    loadFragment(new UsersDisplayFragment());
                    return true;
                case R.id.search:
                    loadFragment(new ReviewsDisplayFragment());
                    return true;
               /* case R.id.collection:
                    loadFragment(new CollectionFragment());
                    return true;*/
                case R.id.profile:
                    loadFragment(new PhotosDisplayFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new UsersDisplayFragment());
        navigation.setSelectedItemId(R.id.home);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.login_frame, fragment);
        transaction.commit();
    }
}
