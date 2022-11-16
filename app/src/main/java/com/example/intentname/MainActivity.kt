package com.example.intentname

import android.annotation.SuppressLint
import android.app.ActionBar.LayoutParams
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_activity_intent.*
import kotlinx.android.synthetic.main.dialog_activity_intent.view.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val logger = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cv_random_title.setOnClickListener { random_change ->
            random_change.visibility = View.INVISIBLE;
        }

        tv_main_best_movie.setOnClickListener {
            showDialog()
        }

        cv_remake_menu1.setOnClickListener(this)
    }

    @SuppressLint("InflateParams")
    private fun showDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        val inflater: LayoutInflater = layoutInflater
        builder.setView(inflater.inflate(R.layout.dialog_activity_intent, null))

        val alertDialog: AlertDialog = builder.create()

        alertDialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)

        alertDialog.show()

        alertDialog.cv_movie_rank.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.cv_movie_rank -> startRankIntent()
            R.id.cv_remake_menu1 -> startMenu1Intent()
            R.id.cv_remake_menu2 -> startMenu2Intent()
            R.id.cv_remake_menu3 -> startMenu3Intent()
        }
    }

    private fun startRankIntent() {
        val movieRankIntent = Intent(this, MovieRankActivity::class.java)
        startActivity(movieRankIntent)
    }

    private fun startMenu1Intent() {
        val movieMenu1Intent = Intent(this, MovieRankActivity::class.java)
        startActivity(movieMenu1Intent)
    }

    private fun startMenu2Intent() {
        val movieMenu2Intent = Intent(this, MovieRankActivity::class.java)
        startActivity(movieMenu2Intent)
    }

    private fun startMenu3Intent() {
        val movieMenu3Intent = Intent(this, MovieRankActivity::class.java)
        startActivity(movieMenu3Intent)
    }
}