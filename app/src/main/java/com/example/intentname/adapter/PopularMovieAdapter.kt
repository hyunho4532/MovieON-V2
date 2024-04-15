package com.example.intentname.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.intentname.R
import com.example.intentname.model.PopularMovie

class PopularMovieAdapter(
    private val popularMovieList: ArrayList<PopularMovie>
) : RecyclerView.Adapter<PopularMovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular_movie_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return popularMovieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(popularMovieList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(popularMovie: PopularMovie) {
            itemView.findViewById<TextView>(R.id.tv_popular_movie).text = popularMovie.title
        }
    }
}