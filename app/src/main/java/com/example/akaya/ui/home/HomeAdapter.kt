package com.example.akaya.ui.home

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.akaya.R
import com.example.akaya.ui.model.offersList
import com.example.akaya.utils.Prefs.Companion.with
import com.squareup.picasso.Picasso

class HomeAdapter(context: Context, var list: List<offersList>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var context: Context = context
    var img_list = list


    //this method is returning                                                                                                                                                       the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_bannerlist, parent, false)
        return ViewHolder(v, img_list)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //mClickListener = btnlistener
        (holder as ViewHolder).bindItems(context)
        Glide.with(context)
            .load(img_list.get(position).image)
            .placeholder(R.mipmap.app_icon)
            .error(R.mipmap.ic_launcher)
            .into(holder.bannerimg)

    }




    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return img_list.size
    }

    class ViewHolder(itemView: View, val img_list: List<offersList>) : RecyclerView.ViewHolder(itemView) {
        val bannerimg: ImageView = itemView.findViewById(R.id.iv_banner) as ImageView

        fun bindItems(ctxt: Context) {
//            productImage.setOnClickListener {
//                //Toast.makeText(ctxt, ""+manuId, Toast.LENGTH_SHORT).show()
//                var intent: Intent = Intent(ctxt, ProductListActivity::class.java)
//                intent.putExtra("manufacId", "" + img_list.get(position).manufacturer_id)
//                Log.e("manufacId", img_list.get(position).manufacturer_id)
//
//                ctxt!!.startActivity(intent)
//            }
        }
    }

}