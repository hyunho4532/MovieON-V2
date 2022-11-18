package com.example.intentname

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.example.intentname.movieList.YourMovieActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_activity_intent.*
import kotlinx.android.synthetic.main.dialog_activity_intent.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cv_random_title.setOnClickListener { random_change ->
            random_change.visibility = View.INVISIBLE
        }

        tv_main_best_movie.setOnClickListener {
            showDialog()
        }

        cv_remake_menu2.setOnClickListener {
            val intent = Intent(this, YourMovieActivity::class.java)
            startActivity(intent)
        }

        cv_remake_menu1.setOnClickListener {
            val intent = Intent(this, MovieRankActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("InflateParams")
    private fun showDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        val inflater: LayoutInflater = layoutInflater
        builder.setView(inflater.inflate(R.layout.dialog_activity_intent, null))

        val alertDialog: AlertDialog = builder.create()

        alertDialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)

        alertDialog.show()
    }
}