package com.example.tripy_v1.Adaptor

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tripy_v1.R
import android.content.Intent
import android.widget.ImageView
import com.example.tripy_v1.Models.Place
import com.example.tripy_v1.Utils.NodejsRetrofitInstance.ServerUrl
import com.example.tripy_v1.View.Home.DetailsActivity
import com.squareup.picasso.Picasso






class PlaceAdaptor(val ListPlace: MutableList<Place>):RecyclerView.Adapter<PlaceAdaptor.ViewHolder>(){

    inner class ViewHolder(val item:View):RecyclerView.ViewHolder(item){
        val tvHotelName:TextView? = itemView.findViewById(R.id.tvHotelName)
        val tvRating:TextView? = itemView.findViewById(R.id.tvRating)
        val tvPlaceDis:TextView? = itemView.findViewById(R.id.tvPlaceDis)
        val place_image:ImageView? = itemView.findViewById(R.id.place_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_item,parent,false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //http://192.168.1.17:8000/img/new-tour-1.jpg
        val url = ServerUrl+"img/"+ ListPlace[position].image
        Log.d("img",url)
        Picasso.get().load(url).fit().centerCrop().into(holder.place_image)
        val name = ListPlace[position].name
        val description = ListPlace[position].description
        val rating = ListPlace[position].rating.toString()
        val img = ListPlace[position].image
        holder.tvHotelName?.text = name
        holder.tvRating?.text = rating
        holder.tvPlaceDis?.text = description
        holder.item.setOnClickListener {
                val intent = Intent(it.context, DetailsActivity::class.java)
                intent.putExtra("EXT_name", name)
                intent.putExtra("EXT_description", description)
                intent.putExtra("EXT_rating", rating)
                intent.putExtra("EXT_img", url)
                it.context.startActivity(intent)
            }
    }
    override fun getItemCount(): Int {
        return ListPlace.size
    }

}