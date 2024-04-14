package com.example.intentname.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.core.view.GravityCompat
import com.example.intentname.R
import com.example.intentname.movieList.YourMovieActivity
import com.example.intentname.movieName.MovieNameActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_navi_header.*

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
            val intent = Intent(this, MovieRankActivity::class.java)
            startActivity(intent)
        }

        cv_remake_menu1.setOnClickListener {
            val intent = Intent(this, MovieNameActivity::class.java)
            startActivity(intent)
        }

        cv_remake_menu3.setOnClickListener {
            val intent = Intent(this, YourMovieActivity::class.java);
            startActivity(intent)
        }

        nav_view.setNavigationItemSelectedListener(object: NavigationView.OnNavigationItemSelectedListener {
            @SuppressLint("CommitPrefEdits", "WorldWriteableFiles")
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                drawer.closeDrawers()

                var id: Int = item.itemId;

                if (id == R.id.account) {
                    tv_welcome_message.visibility = View.INVISIBLE
                    tv_name.visibility = View.VISIBLE

                    val result = intent.getStringExtra("email")

                    tv_name.text = result

                    val sharedPreference = getSharedPreferences("YourEmail", MODE_PRIVATE);
                    val editor: Editor = sharedPreference.edit();

                    editor.putString("Email", tv_name.text.toString())
                    editor.apply()
                }
                else if (id == R.id.setting) {

                }
                else if (id == R.id.logout) {

                }

                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> drawer.openDrawer(GravityCompat.START)
        }

        return super.onOptionsItemSelected(item);
    }

    override fun onStart() {
        super.onStart()


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