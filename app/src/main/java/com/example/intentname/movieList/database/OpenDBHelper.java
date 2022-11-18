package com.example.intentname.movieList.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.intentname.movieList.data.MovieItem;

import java.util.ArrayList;

public class OpenDBHelper extends SQLiteOpenHelper {

    private static final String NAME_DB = "Movie.db";
    private static final int VERSION = 1;

    public OpenDBHelper(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS UserMovie (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT NOT NULL, content TEXT NOT NULL, writeDate TEXT NOT NULL, groupCount TEXT NOT NULL, tag TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public ArrayList<MovieItem> getMovieList() {
        ArrayList<MovieItem> movieItems = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM UserMovie ORDER BY writeDate DESC", null);

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
                String writeDate = cursor.getString(cursor.getColumnIndexOrThrow("writeDate"));
                String groupCount = cursor.getString(cursor.getColumnIndexOrThrow("groupCount"));
                String tag = cursor.getString(cursor.getColumnIndexOrThrow("tag"));

                MovieItem movieItem = new MovieItem();
                movieItem.setId(id);
                movieItem.setTitle(title);
                movieItem.setContent(content);
                movieItem.setWriteDate(writeDate);
                movieItem.setGroupCount(groupCount);
                movieItem.setTag(tag);

                movieItems.add(movieItem);
            }
        }

        cursor.close();

        return movieItems;
    }

    public void InsertMovie(String _title, String _content, String _writeDate, String _groupCount, String _tag) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO UserMovie(title, content, writeDate, groupCount, tag) VALUES('" + _title + "', '" + _content + "', '" + _writeDate + "', '" + _groupCount + "', '" + _tag + "');");
    }

    public void UpdateMovie(String _title, String _content, String _writeDate, String _beforeDate, String _groupCount, String _tag) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE UserMovie SET title='" + _title + "', content='" + _content + "', writeDate='" + _writeDate + "', groupCount='" +_groupCount + "', tag='" + _tag + "' WHERE writeDate='" + _beforeDate + "'");
    }

    public void DeleteMovie(String _beforeDate) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM UserMovie WHERE writeDate='" + _beforeDate + "'");
    }
}
