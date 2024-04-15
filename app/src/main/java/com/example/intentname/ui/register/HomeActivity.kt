package com.example.intentname.ui.register

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


    }
}