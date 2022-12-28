package com.example.tripy_v1.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tripy_v1.R
import com.example.tripy_v1.Models.Country

class DiscoverAdaptor (val ListCountry:MutableList<Country>):RecyclerView.Adapter<DiscoverAdaptor.ViewHolder>(){

    inner class ViewHolder(item: View):RecyclerView.ViewHolder(item){


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.discover_menu_item,parent,false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            val tvCountryName: TextView? = findViewById(R.id.tvCountryName)
            val tvCountryFlag: ImageView? = findViewById(R.id.tvCountryFlag)
            tvCountryName?.text = ListCountry.get(position).CountryName.toString()
            ListCountry[position].ImageUrl?.let { tvCountryFlag?.setImageResource(it) }
        }
    }

    override fun getItemCount(): Int {
       return ListCountry.size
    }
}