package com.example.intentname.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intentname.R
import com.example.intentname.adapter.PopularMovieAdapter
import com.example.intentname.model.PopularMovie
import kotlinx.android.synthetic.main.fragment_home.rv_popular_movie
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val popularMovieList = ArrayList<PopularMovie>()

        GlobalScope.launch(Dispatchers.IO) {

            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://api.themoviedb.org/3/movie/popular?language=ko-KR&page=1")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZjVmZDI3NWVlNTExMWVkNDkzNDVjOTE2YzQ2YzE3NyIsInN1YiI6IjY0MTlhYjgwMGQ1ZDg1MDBiYTEwZDU0MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.IZEX0iX9VfINBnA7RmKA-ImdpxtWyaU1nKl_rvg22KU")
                .build()

            val response = client.newCall(request).execute()

            response.body()?.let {
                val responseData = it.string()

                val jsonObject = JSONObject(responseData)
                val resultsArray = jsonObject.getJSONArray("results")

                for (i in 0 until resultsArray.length()) {
                    val movieObject = resultsArray.getJSONObject(i)

                    val id = movieObject.getString("id")
                    val originalTitle = movieObject.getString("title")
                    val posterPath = "https://image.tmdb.org/t/p/w500/${movieObject.getString("poster_path")}"

                    popularMovieList.add(PopularMovie(id, originalTitle, posterPath))

                    Log.d("HomeFragment", popularMovieList.toString())
                }

                launch(Dispatchers.Main) {

                    rv_popular_movie.adapter = PopularMovieAdapter(requireContext(), popularMovieList)
                    rv_popular_movie.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                }
            }
        }

        return view
    }
}