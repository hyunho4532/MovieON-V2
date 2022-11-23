package com.example.intentname.movieName.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intentname.R;
import com.example.intentname.movieList.YourMovieActivity;
import com.example.intentname.movieName.MovieNameActivity;
import com.example.intentname.movieName.data.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MovieNameAdapter extends RecyclerView.Adapter<MovieNameAdapter.MovieHolder> {

    private List<Movie> items;

    public MovieNameAdapter(List<Movie> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MovieNameAdapter.MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_name_item, parent, false);
        return new MovieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieNameAdapter.MovieHolder holder, int position) {
        Movie item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder {

        private TextView tvMovieNm;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            tvMovieNm = itemView.findViewById(R.id.tv_movieName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), YourMovieActivity.class);
                    intent.putExtra("movieName", tvMovieNm.getText().toString());
                    itemView.getContext().startActivity(intent);
                }
            });
        }

        public void setItem(Movie item) {
            tvMovieNm.setText(item.getMovieNm());
        }
    }
}
