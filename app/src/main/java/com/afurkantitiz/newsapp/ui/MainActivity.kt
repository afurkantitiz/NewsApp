package com.afurkantitiz.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.afurkantitiz.newsapp.R
import com.afurkantitiz.newsapp.utils.gone
import com.afurkantitiz.newsapp.utils.show
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        navController = Navigation.findNavController(this, R.id.navHostFragment)

        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    fun hideNavigationBar() {
        bottomNavigationView.gone()
    }

    fun showNavigationBar() {
        bottomNavigationView.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}