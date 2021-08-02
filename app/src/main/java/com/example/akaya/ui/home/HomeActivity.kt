package com.example.akaya.ui.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.akaya.R
import com.example.akaya.databinding.ActivityHome1Binding

class HomeActivity:AppCompatActivity() {
    lateinit var binding: ActivityHome1Binding
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home1)
        navController = findNavController(R.id.nav_host_fragment)

        //bottom nav
        binding.bottomNavView.setupWithNavController(navController)
        //drawer
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.home_fragment, R.id.search_fragment,R.id.cart_fragment,R.id.whistlist_fragment,R.id.profile_fragment,R.id.Myorderfragment,R.id.Myaddressfragment),
            binding.drawerLayout
        )
        setSupportActionBar(binding.mainToolbar) //Set toolbar

        //menu item click handle
        binding.navView.setupWithNavController(navController)

        //
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    //bottom nav
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) ||
                super.onOptionsItemSelected(item)
    }

    //open drawer when drawer icon clicked and back btn presse
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
    }


}