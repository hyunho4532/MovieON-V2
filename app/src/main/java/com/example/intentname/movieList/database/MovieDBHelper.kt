package com.example.intentname.movieList.database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.intentname.movieList.YourMovieActivity
import com.example.intentname.movieList.data.MovieData
import java.util.prefs.PreferencesFactory

class MovieDBHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE IF NOT EXISTS MovieList (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title TEXT NOT NULL, content TEXT NOT NULL, writeDate TEXT NOT NULL, groupCount TEXT NOT NULL, tag TEXT NOT NULL)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }

    fun getMovieList(): ArrayList<MovieData> {
        val movieItems: ArrayList<MovieData> = ArrayList()

        val db: SQLiteDatabase = readableDatabase

        val cursor: Cursor = db.rawQuery("SELECT * FROM MovieList ORDER BY writeDate DESC", null)

        if (cursor.count != 0) {
            while (cursor.moveToNext()) {
                selectData(cursor, movieData = MovieData())
            }
        }

        cursor.close()

        return movieItems
    }

    private fun selectData(cursor: Cursor, movieData: MovieData) {
        val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
        val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
        val content = cursor.getString(cursor.getColumnIndexOrThrow("content"))
        val writeDate = cursor.getString(cursor.getColumnIndexOrThrow("writeDate"))
        val groupCount = cursor.getString(cursor.getColumnIndexOrThrow("groupCount"))
        val tag = cursor.getString(cursor.getColumnIndexOrThrow("tag"))

        movieData.id = id
        movieData.title = title
        movieData.content = content
        movieData.writeDate = writeDate
        movieData.groupCount = groupCount
        movieData.tag = tag
    }

    fun insertMovie(_title: String, _content: String, _writeDate: String, _groupCount: String, _tag: String) {
        val db: SQLiteDatabase = writableDatabase
        db.execSQL("INSERT INTO MovieList(title, content, writeDate, groupCount, tag) VALUES('$_title', '$_content', '$_writeDate', '$_groupCount', '$_tag');")
    }

    fun updateMovie(_title: String, _content: String, _writeDate: String, _beforeDate: String, _groupCount: String, _tag: String) {
        val db = writableDatabase
        db.execSQL("UPDATE MovieList SET title='$_title', content='$_content', writeDate='$_writeDate', groupCount='$_groupCount', tag='$_tag' WHERE writeDate='$_beforeDate'")
    }

    fun deleteMovie(_beforeDate: String) {
        val db = writableDatabase
        db.execSQL("DELETE FROM MovieList WHERE writeDate='$_beforeDate'")
    }
}