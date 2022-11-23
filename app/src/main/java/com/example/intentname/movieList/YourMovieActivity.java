package com.example.intentname.movieList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intentname.R;
import com.example.intentname.movieList.adapter.MovieListAdapter;
import com.example.intentname.movieList.data.MovieData;
import com.example.intentname.movieList.database.MovieNameHelper;
import com.example.intentname.movieName.MovieNameActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class YourMovieActivity extends AppCompatActivity {

    private RecyclerView mRv_todo;
    private ArrayList<MovieData> mMovieData;
    private MovieNameHelper mMovieNameHelper;
    private MovieListAdapter mAdapter;

    private TextView tv_intentName_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_movie);

        setInit();
    }

    private void setInit() {

        // DB 셋팅
        mMovieNameHelper = new MovieNameHelper(this, "myMovie.db", null, 1);
        mRv_todo = findViewById(R.id.rv_todo);
        FloatingActionButton mBtn_write = findViewById(R.id.btn_write);

        tv_intentName_title = findViewById(R.id.tv_intentName_title);

        mMovieData = new ArrayList<>();

        loadRecentDB();

        mBtn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(YourMovieActivity.this, android.R.style.Theme_Material_Light_Dialog);
                dialog.setContentView(R.layout.dialog_movie_insert);

                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                setParams(dialog, params);

                TextView tv_categories_choice = dialog.findViewById(R.id.tv_categories_choice);

                EditText et_title = dialog.findViewById(R.id.et_title);
                EditText et_content = dialog.findViewById(R.id.et_content);
                EditText et_group_count = dialog.findViewById(R.id.et_group_count);

                Spinner spinner = dialog.findViewById(R.id.spinner_tag);

                final String[] tags = getResources().getStringArray(R.array.spinner_array);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        YourMovieActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        tags
                );

                spinnerSetMenu(spinner, adapter, tv_categories_choice, tags);

                Intent intent = getIntent();
                String movieName = intent.getStringExtra("movieName");
                tv_intentName_title.setText(movieName);

                et_title.setText(tv_intentName_title.getText().toString());

                Button btn_ok = dialog.findViewById(R.id.btn_ok);
                Button btn_movie_list = dialog.findViewById(R.id.btn_movie_list);

                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String currentTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                        mMovieNameHelper.insertMovie(et_title.getText().toString(), et_content.getText().toString(), currentTime, et_group_count.getText().toString(), tv_categories_choice.getText().toString());

                        MovieData movieData = new MovieData();

                        movieDataS(movieData, currentTime);

                        mAdapter.addItem(movieData);
                        mRv_todo.smoothScrollToPosition(0);

                        dialog.dismiss();

                        Toast.makeText(YourMovieActivity.this, "할일 목록에 추가되었습니다.", Toast.LENGTH_SHORT).show();
                    }

                    private void movieDataS(MovieData movieData, String currentTime) {
                        movieData.setTitle(et_title.getText().toString());
                        movieData.setContent(et_content.getText().toString());
                        movieData.setWriteDate(currentTime);
                        movieData.setGroupCount(et_group_count.getText().toString());
                        movieData.setTag(tv_categories_choice.getText().toString());
                    }
                });

                btn_movie_list.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(YourMovieActivity.this, MovieNameActivity.class);
                        startActivity(intent);
                    }
                });

                dialog.show();
            }

            private void spinnerSetMenu(Spinner spinner, ArrayAdapter<String> adapter, TextView tv_categories_choice, String[] tags) {
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        tv_categories_choice.setText(tags[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                spinner.setAdapter(adapter);
                spinner.setSelection(0);
            }
        });
    }

    private void setParams(Dialog dialog, WindowManager.LayoutParams params) {
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes((WindowManager.LayoutParams) params);
    }

    private void loadRecentDB() {
        mMovieData = mMovieNameHelper.getMovieList();

        if (mAdapter == null) {
            mAdapter = new MovieListAdapter(mMovieData, this);
            mRv_todo.setHasFixedSize(true);
            mRv_todo.setAdapter(mAdapter);
        }
    }
}