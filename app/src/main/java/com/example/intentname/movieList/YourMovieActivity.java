package com.example.intentname.movieList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.intentname.R;
import com.example.intentname.movieList.adapter.MovieListAdapter;
import com.example.intentname.movieList.data.MovieData;
import com.example.intentname.movieList.database.MovieDBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class YourMovieActivity extends AppCompatActivity {

    private RecyclerView mRv_todo;
    private FloatingActionButton mBtn_write;
    private ArrayList<MovieData> mMovieData;
    private MovieDBHelper mDBHelper;
    private MovieListAdapter mAdapter;

    private Spinner spinner;

    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_movie);

        setInit();
    }

    private void setInit() {
        mDBHelper = new MovieDBHelper(this, "myMovie.db", null, 1);
        mRv_todo = findViewById(R.id.rv_todo);
        mBtn_write = findViewById(R.id.btn_write);

        mMovieData = new ArrayList<>();

        loadRecentDB();

        mBtn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(YourMovieActivity.this, android.R.style.Theme_Material_Light_Dialog);
                dialog.setContentView(R.layout.dialog_movie_insert);

                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.height = WindowManager.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setAttributes((WindowManager.LayoutParams) params);

                EditText et_title = dialog.findViewById(R.id.et_title);
                EditText et_content = dialog.findViewById(R.id.et_content);
                EditText et_group_count = dialog.findViewById(R.id.et_group_count);
                EditText et_tag = dialog.findViewById(R.id.et_tag);

                Button btn_ok = dialog.findViewById(R.id.btn_ok);

                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String currentTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                        mDBHelper.insertMovie(et_title.getText().toString(), et_content.getText().toString(), currentTime, et_group_count.getText().toString(), et_tag.getText().toString());

                        MovieData movieData = new MovieData();
                        movieData.setTitle(et_title.getText().toString());
                        movieData.setContent(et_content.getText().toString());
                        movieData.setWriteDate(currentTime);
                        movieData.setGroupCount(et_group_count.getText().toString());
                        movieData.setTag(et_tag.getText().toString());

                        mAdapter.addItem(movieData);

                        dialog.dismiss();

                        Toast.makeText(YourMovieActivity.this, "할일 목록에 추가되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();
            }
        });
    }

    private void loadRecentDB() {

        mMovieData = mDBHelper.getMovieList();

        if (mAdapter == null) {
            mAdapter = new MovieListAdapter(mMovieData, this);
            mRv_todo.setHasFixedSize(true);
            mRv_todo.setAdapter(mAdapter);
        }
    }
}