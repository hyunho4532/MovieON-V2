package com.example.intentname.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.intentname.R
import com.example.intentname.ui.fragment.HomeFragment
import com.example.intentname.ui.fragment.MovieFragment
import com.example.intentname.ui.fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_home.bottom_navigation_view

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation_view.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home_menu -> {
                    replaceFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.movie_menu -> {
                    replaceFragment(MovieFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.profile_menu -> {
                    replaceFragment(ProfileFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}