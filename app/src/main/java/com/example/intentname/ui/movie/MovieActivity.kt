package com.example.intentname.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.intentname.databinding.ActivityDetailMovieBinding
import com.example.intentname.viewmodel.MovieIdStatusViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class MovieActivity : AppCompatActivity() {

    private lateinit var activityDetailMovieBinding: ActivityDetailMovieBinding

    private lateinit var movieIdStatusViewModel: MovieIdStatusViewModel

    companion object {
        const val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZjVmZDI3NWVlNTExMWVkNDkzNDVjOTE2YzQ2YzE3NyIsInN1YiI6IjY0MTlhYjgwMGQ1ZDg1MDBiYTEwZDU0MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.IZEX0iX9VfINBnA7RmKA-ImdpxtWyaU1nKl_rvg22KU"
        const val LANGUAGE = "ko-KR"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(activityDetailMovieBinding.root)

        movieIdStatusViewModel = ViewModelProvider(this)[MovieIdStatusViewModel::class.java]

        val movieId = intent.getStringExtra("movie_id")

        val url = "https://api.themoviedb.org/3/movie/${movieId}/videos?language=$LANGUAGE"

        fetchVideoData(url, activityDetailMovieBinding)
    }

    private fun fetchVideoData(url: String, activityDetailMovieBinding: ActivityDetailMovieBinding) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .get()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer $API_KEY")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()

            }

            override fun onResponse(call: Call, response: Response) {
                response.body()?.let {
                    try {
                        val responseData = it.string()

                        val jsonResponse = JSONObject(responseData)

                        val resultsArray = jsonResponse.getJSONArray("results")

                        if (resultsArray.length() > 0) {
                            val trailer = resultsArray.getJSONObject(0)
                            val trailerKey = trailer.getString("key")

                            Log.d("MovieActivity2", trailerKey)

                            runOnUiThread {
                                initializePlayer(trailerKey, activityDetailMovieBinding)
                            }
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

        })
    }

    private fun initializePlayer(
        trailerKey: String,
        activityDetailMovieBinding: ActivityDetailMovieBinding
    ) {
        runOnUiThread {
            activityDetailMovieBinding.youtubePlayerView.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)

                    youTubePlayer.loadVideo(trailerKey, 0F)
                }
            })
        }
    }
}