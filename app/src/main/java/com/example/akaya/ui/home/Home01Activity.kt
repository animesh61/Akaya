package com.example.akaya.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.akaya.R
import com.example.akaya.ui.address.MyAddressActivity
import com.example.akaya.ui.address.SelectAddressActivity
import com.example.akaya.ui.cart.MyCartActivity
import com.example.akaya.ui.location.LocationActivity
import com.example.akaya.ui.orders.MyOrdersActivity
import com.example.akaya.ui.orders.OrdersActivity
import com.example.akaya.ui.payments.PaymentsActivity
import com.example.akaya.ui.payments.SelectPaymentActivity
import com.example.akaya.ui.product.ProductNameActivity
import com.example.akaya.ui.profile.ProfileActivity
import com.example.akaya.ui.schedule.CreateScheduleActivity
import com.example.akaya.ui.wishlist.MyWishlistActivity

class Home01Activity : AppCompatActivity() {

    private lateinit var topImage: ImageView
    private lateinit var bottomProfile: FrameLayout
    private lateinit var bottomHome: FrameLayout
    private lateinit var bottomSearch: FrameLayout
    private lateinit var bottomCart: FrameLayout
    private lateinit var bottomWishlist: FrameLayout

    private lateinit var searchParent: LinearLayout
    private lateinit var catFruits: LinearLayout
    private lateinit var catVegies: LinearLayout
    private lateinit var catDairy: LinearLayout
    private lateinit var catMeat: LinearLayout
    private lateinit var product01: CardView
    private lateinit var product02: CardView

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

        searchParent = findViewById(R.id.home_SearchLayout)
        searchParent.setOnClickListener {
            val i = Intent(this, SelectAddressActivity::class.java)
            startActivity(i)
        }

        catFruits = findViewById(R.id.home_CatFruits)
        catFruits.setOnClickListener {
            val i = Intent(this, MyCartActivity::class.java)
            startActivity(i)
        }

        catVegies = findViewById(R.id.home_CatVegies)
        catVegies.setOnClickListener {
            val i = Intent(this, MyOrdersActivity::class.java)
            startActivity(i)
        }

        catDairy = findViewById(R.id.home_CatDairy)
        catDairy.setOnClickListener {
            val i = Intent(this, SelectPaymentActivity::class.java)
            startActivity(i)
        }

        catMeat = findViewById(R.id.home_CatMeat)
        catMeat.setOnClickListener {
            val i = Intent(this, ProductNameActivity::class.java)
            startActivity(i)
        }

        product01 = findViewById(R.id.home_Product01)
        product01.setOnClickListener {
            val i = Intent(this, CreateScheduleActivity::class.java)
            startActivity(i)
        }

        product02 = findViewById(R.id.home_Product02)
        product02.setOnClickListener {
            val i = Intent(this, MyWishlistActivity::class.java)
            startActivity(i)
        }
    }
}