package com.example.intentname;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.intentname.adapter.MyAdapter;
import com.example.intentname.retrofit.RetrofitInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRankActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter<MyAdapter.MyViewHolder> mAdapter;
    private TextView tv_calendar_select;

    Calendar myCalendar = Calendar.getInstance();

    String baseUrl = "http://www.kobis.or.kr";
    String API_KEY = "79087d2a1af27f32f2300b1619a278d8";
    Retrofit retrofit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_rank);

        recyclerView = findViewById(R.id.day_movie_recycler);
        CardView cv_calendar_select_click = findViewById(R.id.cv_calendar_select_click);

        tv_calendar_select = findViewById(R.id.tv_calendar_select);

        CardView cv_movie_calendar_click = findViewById(R.id.cv_movie_calendar_click);

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        cv_movie_calendar_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MovieRankActivity.this, myDatePicker,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        cv_calendar_select_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retrofitInterface.getBoxOffice(API_KEY, tv_calendar_select.getText().toString(), 0).enqueue(new Callback<Map<String, Object>>() {

                    @Override
                    public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                        Map<String, Object> boxOfficeResult = (Map<String, Object>) response.body().get("boxOfficeResult");
                        ArrayList<Map<String, Object>> jsonList = (ArrayList<Map<String, Object>>) boxOfficeResult.get("dailyBoxOfficeList");
                        mAdapter = new MyAdapter(jsonList);
                        recyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<Map<String, Object>> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void updateLabel() {
        String myFormat = "yyyyMMdd";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.KOREA);
        tv_calendar_select.setText(simpleDateFormat.format(myCalendar.getTime()));
    }

    public void click_btn(View view) {

    }
}