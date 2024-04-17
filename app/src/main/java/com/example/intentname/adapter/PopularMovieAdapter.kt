package com.example.intentname.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.intentname.R
import com.example.intentname.model.PopularMovie
import com.example.intentname.presenter.MovieDetailPresenter
import com.example.intentname.ui.movie.MovieActivity

class PopularMovieAdapter(
    private val context: Context,
    private val popularMovieList: ArrayList<PopularMovie>
) : RecyclerView.Adapter<PopularMovieAdapter.ViewHolder>() {

    private val movieDetailPresenter = MovieDetailPresenter()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular_movie_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return popularMovieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(popularMovieList[position], context, movieDetailPresenter)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(popularMovie: PopularMovie, context: Context, movieDetailPresenter: MovieDetailPresenter) {

            Glide.with(context)
                .load(popularMovie.posterPath)
                .into(itemView.findViewById(R.id.iv_popular_movie))

            itemView.setOnClickListener {
                val intent = Intent(context, MovieActivity::class.java)
                intent.putExtra("movie_id", popularMovie.id)
                context.startActivity(intent)
            }
        }
    }
}