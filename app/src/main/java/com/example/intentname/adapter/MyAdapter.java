package com.example.intentname.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intentname.R;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public ArrayList<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

    public MyAdapter(ArrayList<Map<String, Object>> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Map<String, Object> item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvRank, tvMovieNm, tvOpenDt, tvRankInTen;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRank = itemView.findViewById(R.id.tv_rank);
            tvMovieNm = itemView.findViewById(R.id.tv_movieNm);
            tvOpenDt = itemView.findViewById(R.id.tv_openDt);
            tvRankInTen = itemView.findViewById(R.id.tvRankInTen);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void setItem(Map<String, Object> item) {
            tvRank.setText(Objects.requireNonNull(item.get("rank")).toString());
            tvMovieNm.setText(Objects.requireNonNull(item.get("movieNm")).toString());
            tvOpenDt.setText(Objects.requireNonNull(item.get("openDt")).toString());
            tvRankInTen.setText(Objects.requireNonNull(item.get("rankInten")).toString());

        }
    }
}
