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
import com.example.intentname.movieList.data.TodoItem;
import com.example.intentname.movieList.database.DBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    private ArrayList<TodoItem> todoItems;
    private Context mContext;
    private DBHelper mDBHelper;

    public MovieListAdapter(ArrayList<TodoItem> todoItems, Context mContext) {
        this.todoItems = todoItems;
        this.mContext = mContext;
        mDBHelper = new DBHelper(mContext);
    }

    @NonNull
    @Override
    public MovieListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_movie_list, parent, false);
        return new MyViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MyViewHolder holder, int position) {
        holder.tv_title.setText(todoItems.get(position).getTitle());
        holder.tv_content.setText(todoItems.get(position).getContent());
        holder.tv_writeDate.setText(todoItems.get(position).getWriteDate());
    }

    @Override
    public int getItemCount() {
        return todoItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private TextView tv_content;
        private TextView tv_writeDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_writeDate = itemView.findViewById(R.id.tv_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int curPos = getAdapterPosition();
                    TodoItem todoItem = todoItems.get(curPos);

                    String[] strChoiceItems =  { "수정하기", "삭제하기" };
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                    builder.setTitle("원하는 작업을 선택하세요!");
                    builder.setItems(strChoiceItems, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            if (which == 0) {
                                Dialog dialog = new Dialog(mContext, android.R.style.Theme_Material_Light_Dialog);
                                dialog.setContentView(R.layout.dialog_movie_insert);

                                EditText et_title = dialog.findViewById(R.id.et_title);
                                EditText et_content = dialog.findViewById(R.id.et_content);
                                Button btn_ok = dialog.findViewById(R.id.btn_ok);

                                btn_ok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String title = et_title.getText().toString();
                                        String content = et_content.getText().toString();
                                        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                                        String beforeTime = todoItem.getWriteDate();

                                        mDBHelper.UpdateMovie(title, content, currentTime, beforeTime);

                                        todoItem.setTitle(title);
                                        todoItem.setContent(content);
                                        todoItem.setWriteDate(currentTime);

                                        notifyItemChanged(curPos, todoItem);

                                        dialog.dismiss();

                                        Toast.makeText(mContext, "목록 수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                dialog.show();
                            }
                            else if (which == 1) {
                                String beforeTime = todoItem.getWriteDate();

                                mDBHelper.DeleteMovie(beforeTime);

                                todoItems.remove(curPos);
                                notifyItemRemoved(curPos);

                                Toast.makeText(mContext, "목록이 제거 되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.show();
                }
            });
        }
    }

    public void addItem(TodoItem _item) {
        todoItems.add(0, _item);
        notifyItemInserted(0);
    }
}
