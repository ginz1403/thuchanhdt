package com.example.nguyenngoctan_19dh110190;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private List<Food> mFoods;
    /* access modifiers changed from: private */
    public OnFoodItemClickListener mListener;


    public interface OnFoodItemClickListener {
        void onFoodItemClick(Food food);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvName;
        TextView tvPrice;
        TextView tvRate;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvRate = itemView.findViewById(R.id.tvRate);
        }
    }

    public FoodAdapter(List<Food> foods, TopFoodFragment listener) {
        mFoods = foods;


    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_food, parent, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        Food food = mFoods.get(position);
        FirebaseStorage.getInstance().getReference().child("foods/" + food.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(viewHolder.ivImage);
            }
        });
        viewHolder.tvName.setText(food.getName());
        viewHolder.tvPrice.setText(food.getPrice() + "");
        viewHolder.tvRate.setText("Rate: " + food.getRate());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FoodAdapter.this.mListener.onFoodItemClick(food);
            }
        });
    }

    public int getItemCount() {
        return mFoods.size();
    }
}
