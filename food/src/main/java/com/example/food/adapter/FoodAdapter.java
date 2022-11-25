package com.example.food.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.R;
import com.example.food.data.Row;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyFoodHolder> {

    private final List<Row> items;

    public FoodAdapter(List<Row> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public FoodAdapter.MyFoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_food_item, parent, false);

        return new MyFoodHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.MyFoodHolder holder, int position) {
        Row row = items.get(position);
        holder.setMovie(row);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyFoodHolder extends RecyclerView.ViewHolder {

        private final TextView tvFoodNm;

        public MyFoodHolder(@NonNull View itemView) {
            super(itemView);

            tvFoodNm = itemView.findViewById(R.id.tv_foodNm);
        }

        public void setMovie(Row row) {
            tvFoodNm.setText(row.getRcpNm());
        }
    }
}
