package com.admin.foodlabrinthadmin.Handler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.foodlabrinthadmin.Model.Reviews;
import com.admin.foodlabrinthadmin.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewVH> {

    private Context context;
    private List<Reviews> reviewsData;

    public ReviewsAdapter(Context context, List<Reviews> reviewsData) {
        this.context = context;
        this.reviewsData = reviewsData;
    }

    @NonNull
    @Override
    public ReviewsAdapter.ReviewVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.review_cell, parent, false);
        return new ReviewsAdapter.ReviewVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsAdapter.ReviewVH holder, int position) {
        Reviews item = reviewsData.get(position);
        if(item!=null) {
            // Log.e("pos",trendingData.get(i).getName()+" "+i);
            holder.review.setText(reviewsData.get(position).getText());
            holder.date.setText(reviewsData.get(position).getDate());
            holder.resRating.setRating(Float.parseFloat(reviewsData.get(position).getStars()));
        }
        else {
            Toast.makeText(context, "Something went wrong in Adapter!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return reviewsData.size();
    }

    public class ReviewVH extends RecyclerView.ViewHolder {

        private TextView review;
        private TextView date;
        private RatingBar resRating;
        ImageButton delete;

        public ReviewVH(@NonNull View itemView) {
            super(itemView);
            review = (TextView) itemView.findViewById(R.id.review_text);
            date = (TextView) itemView.findViewById(R.id.review_date);
            resRating = (RatingBar) itemView.findViewById(R.id.resRating);
            delete = (ImageButton) itemView.findViewById(R.id.deleteReview);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference driverRef = FirebaseDatabase.getInstance().getReference().child("reviews");
                    driverRef.removeValue();
                }
            });
        }
    }
}
