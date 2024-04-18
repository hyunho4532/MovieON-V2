package com.example.intentname.ui.movie

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.intentname.R
import com.example.intentname.model.DetailMovie
import com.example.intentname.presenter.MovieDetailPresenter
import com.example.intentname.ui.fragment.HomeFragment
import com.example.intentname.ui.fragment.MovieFragment
import com.example.intentname.ui.fragment.ProfileFragment
import com.example.intentname.view.View
import kotlinx.android.synthetic.main.activity_detail_movie.tv_movie_detail_id
import kotlinx.android.synthetic.main.activity_home.bottom_navigation_view

class MovieActivity : AppCompatActivity() {

    companion object {
        const val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZjVmZDI3NWVlNTExMWVkNDkzNDVjOTE2YzQ2YzE3NyIsInN1YiI6IjY0MTlhYjgwMGQ1ZDg1MDBiYTEwZDU0MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.IZEX0iX9VfINBnA7RmKA-ImdpxtWyaU1nKl_rvg22KU"
        const val LANGUAGE = "en-US"
        const val VIDEO_BASE_URL = "https://www.youtube.com/watch?v="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val movieId = intent.getStringExtra("movie_id")

        tv_movie_detail_id.text = movieId
    }
}