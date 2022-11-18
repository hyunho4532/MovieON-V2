package com.example.intentname.movieList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.R.layout;
import android.annotation.SuppressLint;
import android.app.Dialog;
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
import com.example.intentname.movieList.data.MovieItem;
import com.example.intentname.movieList.database.OpenDBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class YourMovieActivity extends AppCompatActivity {

    private RecyclerView mRv_todo;
    private FloatingActionButton mBtn_write;
    private ArrayList<MovieItem> mMovieItems;
    private OpenDBHelper mDBHelper;
    private MovieListAdapter mAdapter;

    private Spinner spinner;

    private ArrayAdapter<CharSequence> adapter;

    String[] items = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_movie);

        setInit();
    }

    private void setInit() {
        mDBHelper = new OpenDBHelper(this);
        mRv_todo = findViewById(R.id.rv_todo);
        mBtn_write = findViewById(R.id.btn_write);

        mMovieItems = new ArrayList<>();

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

                        mDBHelper.InsertMovie(et_title.getText().toString(), et_content.getText().toString(), currentTime, et_group_count.getText().toString(), et_tag.getText().toString());

                        MovieItem item = new MovieItem();
                        item.setTitle(et_title.getText().toString());
                        item.setContent(et_content.getText().toString());
                        item.setWriteDate(currentTime);
                        item.setGroupCount(et_group_count.getText().toString());
                        item.setTag(et_tag.getText().toString());

                        mAdapter.addItem(item);

                        dialog.dismiss();

                        Toast.makeText(YourMovieActivity.this, "할일 목록에 추가되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();
            }
        });
    }

    private void loadRecentDB() {

        mMovieItems = mDBHelper.getMovieList();

        if (mAdapter == null) {
            mAdapter = new MovieListAdapter(mMovieItems, this);
            mRv_todo.setHasFixedSize(true);
            mRv_todo.setAdapter(mAdapter);
        }
    }
}