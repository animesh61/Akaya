package com.example.akaya.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.akaya.R
import com.example.akaya.ui.address.MyAddressActivity
import com.example.akaya.ui.location.LocationActivity
import com.example.akaya.ui.orders.OrdersActivity
import com.example.akaya.ui.payments.PaymentsActivity
import com.example.akaya.ui.profile.ProfileActivity

class Home01Activity : AppCompatActivity() {

    private lateinit var topImage: ImageView
    private lateinit var bottomProfile: FrameLayout
    private lateinit var bottomHome: FrameLayout
    private lateinit var bottomSearch: FrameLayout
    private lateinit var bottomCart: FrameLayout
    private lateinit var bottomWishlist: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        topImage = findViewById(R.id.home_TopImage)
        topImage.setOnClickListener {
            val i = Intent(this, Home02Activity::class.java)
            startActivity(i)
        }

        bottomHome = findViewById(R.id.home_BottomHome)
        bottomHome.setOnClickListener {
            val i = Intent(this, MyAddressActivity::class.java)
            startActivity(i)
        }

        bottomSearch = findViewById(R.id.home_BottomSearch)
        bottomSearch.setOnClickListener {
            val i = Intent(this, LocationActivity::class.java)
            startActivity(i)
        }

        bottomCart = findViewById(R.id.home_BottomCart)
        bottomCart.setOnClickListener {
            val i = Intent(this, OrdersActivity::class.java)
            startActivity(i)
        }

        bottomWishlist = findViewById(R.id.home_BottomWishlist)
        bottomWishlist.setOnClickListener {
            val i = Intent(this, PaymentsActivity::class.java)
            startActivity(i)
        }

        bottomProfile = findViewById(R.id.home_BottomProfile)
        bottomProfile.setOnClickListener {
            val i = Intent(this, ProfileActivity::class.java)
            startActivity(i)
        }
    }
}