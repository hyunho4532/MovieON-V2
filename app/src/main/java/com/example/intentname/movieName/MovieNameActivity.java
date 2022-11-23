package com.example.intentname.movieName;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.intentname.R;
import com.example.intentname.movieName.adapter.MovieNameAdapter;
import com.example.intentname.movieName.data.Movie;
import com.example.intentname.movieName.data.MovieListResult;
import com.example.intentname.movieName.data.Result;
import com.example.intentname.retrofit.RetrofitClient;
import com.example.intentname.retrofit.RetrofitNameData;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieNameActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    String baseUrl = "http://www.kobis.or.kr";
    String API_KEY = "79087d2a1af27f32f2300b1619a278d8";
    Retrofit retrofit;

    private EditText et_movie_name;

    private RetrofitClient retrofitClient;
    private RetrofitNameData retrofitNameData;

    private Button btn_test;
    private TextView tv_name_title;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_name);

        mRecyclerView = findViewById(R.id.movie_name_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        retrofitClient = RetrofitClient.getInstance();
        retrofitNameData = RetrofitClient.getRetrofitNameData();

        et_movie_name = findViewById(R.id.et_movie_name);
        tv_name_title = findViewById(R.id.tv_name_title);

        btn_test = findViewById(R.id.btn_test);

        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofitNameData.getNameOffice(API_KEY, et_movie_name.getText().toString()).enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        Result result = response.body();
                        MovieListResult movieListResult = result.getMovieListResult();
                        mAdapter = new MovieNameAdapter(movieListResult.getMovieList());

                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {

                    }
                });
            }
        });
    }
}