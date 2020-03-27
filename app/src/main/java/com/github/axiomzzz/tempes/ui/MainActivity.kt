package com.github.axiomzzz.tempes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.github.axiomzzz.tempes.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var host: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar) // Add custom toolbar

        host=supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment? ?: return

        navController=host.navController

        NavigationUI.setupActionBarWithNavController(this, navController)  // Add navController in toolbar !!!

        bottom_nav.setupWithNavController(navController)

        // Block double click

        bottom_nav.setOnNavigationItemReselectedListener {
            it.isEnabled=true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }
}
