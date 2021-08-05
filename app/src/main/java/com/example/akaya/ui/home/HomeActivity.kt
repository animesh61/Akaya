package com.example.akaya.ui.home

import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ListView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.akaya.R
import kotlinx.android.synthetic.main.activity_home1.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class HomeActivity:AppCompatActivity() {
    //lateinit var binding: ActivityHome1Binding
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    private var fl_fragment_container: FrameLayout? = null
    private var madapter:ArrayAdapter<String>?=null
    private var nav_list:ListView?=null
    private var mFragment: Fragment? = null
    private var mFragmentManager: FragmentManager? = null
    private var mFragmentTransaction: FragmentTransaction? = null

    val Home = "FragmentHome"
    var selectedNavItemIndex: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home1)
        // addMenuItemInNavMenuDrawer()

        // nav_view.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(
            this, app_drawer_layout, apptoolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        app_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        fl_fragment_container = findViewById(R.id.fl_fragment_container)
        nav_list=findViewById(R.id.nav_list)as ListView

        addDrawerItems()
        mFragment = this.supportFragmentManager.findFragmentById(R.id.fl_fragment_container)
        mFragmentManager = supportFragmentManager
        loadFragment(Home, false, HomeFragment())
        // apptoolbar.setTitle("Home")
        //  nav_view.getMenu().getItem(0).setChecked(true)
        selectedNavItemIndex = 0

//        binding = DataBindingUtil.setContentView(this, R.layout.activity_home1)
//        navController = findNavController(R.id.nav_host_fragment)
//
//        //bottom nav
//        binding.bottomNavView.setupWithNavController(navController)
//        //drawer
//        appBarConfiguration = AppBarConfiguration(
//            setOf(R.id.home_fragment, R.id.search_fragment,R.id.cart_fragment,R.id.whistlist_fragment,R.id.profile_fragment,R.id.Myorderfragment,R.id.Myaddressfragment),
//            binding.drawerLayout
//        )
//        setSupportActionBar(binding.mainToolbar) //Set toolbar
//
//        //menu item click handle
//        binding.navView.setupWithNavController(navController)
//
//        //
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//    }
//
//    //bottom nav
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return item.onNavDestinationSelected(navController) ||
//                super.onOptionsItemSelected(item)
//    }
//
//    //open drawer when drawer icon clicked and back btn presse
//    override fun onSupportNavigateUp(): Boolean {
//        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
//    }
//
    }

    private fun addDrawerItems() {
        val otherStrings = arrayOf("Home", "My Profile", "My Orders","My Address","My Cart","Logout")
        madapter =  ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, otherStrings);
        nav_list!!.setAdapter(madapter);
    }

    fun loadFragment(tag: String, addToStack: Boolean, setFragment: Fragment) {
        mFragmentTransaction = mFragmentManager!!.beginTransaction()
        mFragment = setFragment

        if (addToStack) {
            //mFragmentTransaction.setCustomAnimations(R.anim.activity_open_translate, R.anim.activity_close_translate);
            mFragmentTransaction!!.add(R.id.fl_fragment_container, mFragment!!, tag)
            //mFragmentTransaction.setCustomAnimations(R.anim.activity_open_translate, R.anim.activity_close_translate);
            mFragmentTransaction!!.addToBackStack(tag).commit()
        } else {
            //mFragmentTransaction.setCustomAnimations(R.anim.activity_open_translate, R.anim.activity_close_translate);
            mFragmentTransaction!!.replace(R.id.fl_fragment_container, mFragment!!, tag)
            mFragmentTransaction!!.addToBackStack(tag)
            mFragmentTransaction!!.commit()
        }


    }



}