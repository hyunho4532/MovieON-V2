package com.example.intentname.movieList.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intentname.R;
import com.example.intentname.movieList.data.MovieData;
import com.example.intentname.movieList.database.MovieDBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    private ArrayList<MovieData> movieData;
    private Context mContext;
    private MovieDBHelper mDBHelper;

    public MovieListAdapter(ArrayList<MovieData> movieData, Context mContext) {
        this.movieData = movieData;
        this.mContext = mContext;
        mDBHelper = new MovieDBHelper(mContext, "myMovie.db", null, 1);
    }

    @NonNull
    @Override
    public MovieListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_movie_list, parent, false);
        return new MyViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MyViewHolder holder, int position) {
        holder.tv_title.setText(movieData.get(position).getTitle());
        holder.tv_content.setText(movieData.get(position).getContent());
        holder.tv_writeDate.setText(movieData.get(position).getWriteDate());
        holder.tv_tag.setText(movieData.get(position).getTag());
        holder.tv_group_count.setText(movieData.get(position).getGroupCount());
    }

    @Override
    public int getItemCount() {
        return movieData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private TextView tv_content;
        private TextView tv_writeDate;
        private TextView tv_tag;
        private TextView tv_group_count;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_writeDate = itemView.findViewById(R.id.tv_date);
            tv_tag = itemView.findViewById(R.id.tv_tag);
            tv_group_count = itemView.findViewById(R.id.tv_group_count);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int curPos = getAdapterPosition();
                    MovieData movieItem = movieData.get(curPos);

                    String[] strChoiceItems = { "수정하기", "삭제하기" };

                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                    builder.setTitle("원하는 작업을 선택해주세요");

                    builder.setItems(strChoiceItems, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int position) {
                            if (position == 0) {
                                Dialog dialog = new Dialog(mContext, android.R.style.Theme_Material_Light_Dialog);
                                dialog.setContentView(R.layout.dialog_movie_insert);

                                EditText et_title = dialog.findViewById(R.id.et_title);
                                EditText et_content = dialog.findViewById(R.id.et_content);
                                EditText et_group_count = dialog.findViewById(R.id.et_group_count);
                                EditText et_tag = dialog.findViewById(R.id.et_tag);

                                Button btn_ok = dialog.findViewById(R.id.btn_ok);

                                et_title.setText(movieItem.getTitle());
                                et_content.setText(movieItem.getContent());
                                et_group_count.setText(movieItem.getGroupCount());
                                et_tag.setText(movieItem.getTag());

                                btn_ok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        String title = et_title.getText().toString();
                                        String content = et_content.getText().toString();
                                        String currentTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                                        String beforeTime = movieItem.getWriteDate();
                                        String groupCount = et_group_count.getText().toString();
                                        String tag = et_tag.getText().toString();

                                        mDBHelper.updateMovie(title, content, currentTime, beforeTime, groupCount, tag);

                                        movieItem.setTitle(title);
                                        movieItem.setContent(content);
                                        movieItem.setWriteDate(currentTime);
                                        movieItem.setGroupCount(groupCount);
                                        movieItem.setTag(tag);

                                        notifyItemChanged(curPos, movieItem);
                                        dialog.dismiss();

                                        Toast.makeText(mContext, "목록 수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                dialog.show();
                            }
                            else if (position == 1) {
                                String beforeTime = movieItem.getWriteDate();
                                mDBHelper.deleteMovie(beforeTime);

                                movieData.remove(curPos);
                                notifyItemRemoved(curPos);

                                Toast.makeText(mContext, "목록 삭제가 완료되었습니다.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    builder.show();
                }
            });
        }
    }

    public void addItem(MovieData _data) {
        movieData.add(0, _data);
        notifyItemInserted(0);
    }
}