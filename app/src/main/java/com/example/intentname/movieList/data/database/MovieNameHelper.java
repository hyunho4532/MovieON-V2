package com.example.intentname.movieList.data.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.example.intentname.movieList.data.MovieData;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MovieNameHelper extends SQLiteOpenHelper {

    public MovieNameHelper(@androidx.annotation.Nullable Context context, @androidx.annotation.Nullable String name, @androidx.annotation.Nullable CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(@Nullable SQLiteDatabase db) {
        if (db != null) {
            db.execSQL("CREATE TABLE IF NOT EXISTS MovieList (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, content TEXT NOT NULL, writeDate TEXT NOT NULL, groupCount TEXT NOT NULL, tag TEXT NOT NULL)");
        }

    }

    public void onUpgrade(@Nullable SQLiteDatabase db, int oldVersion, int newVersion) {
        this.onCreate(db);
    }

    @NotNull
    public ArrayList<MovieData> getMovieList() {
        ArrayList<MovieData> movieDataS = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM MovieList ORDER BY writeDate DESC", null);

        if (cursor.getCount() != 0) {
            while(cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
                String writeDate = cursor.getString(cursor.getColumnIndexOrThrow("writeDate"));
                String groupCount = cursor.getString(cursor.getColumnIndexOrThrow("groupCount"));
                String tag = cursor.getString(cursor.getColumnIndexOrThrow("tag"));

                MovieData movieData = new MovieData();
                movieData.setId(id);
                movieData.setTitle(title);
                movieData.setContent(content);
                movieData.setWriteDate(writeDate);
                movieData.setGroupCount(groupCount);
                movieData.setTag(tag);

                movieDataS.add(movieData);
            }
        }

        cursor.close();

        return movieDataS;
    }

    public int countMovie() {

        int movieCount = 0;

        String sql = "SELECT COUNT(*) FROM MovieList";

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            movieCount = cursor.getInt(0);
        }

        cursor.close();

        return movieCount;
    }

    public void insertMovie(@NotNull String _title, @NotNull String _content, @NotNull String _writeDate, @NotNull String _groupCount, @NotNull String _tag) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO MovieList(title, content, writeDate, groupCount, tag) VALUES('" + _title + "', '" + _content + "', '" + _writeDate + "', '" + _groupCount + "', '" + _tag + "');");
    }

    public void updateMovie(@NotNull String _title, @NotNull String _content, @NotNull String _writeDate, @NotNull String _beforeDate, @NotNull String _groupCount, @NotNull String _tag) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE MovieList SET title='" + _title + "', content='" + _content + "', writeDate='" + _writeDate + "', groupCount='" + _groupCount + "', tag='" + _tag + "' WHERE writeDate='" + _beforeDate + '\'');
    }

    public void deleteMovie(@NotNull String _beforeDate) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM MovieList WHERE writeDate='" + _beforeDate + '\'');
    }
}