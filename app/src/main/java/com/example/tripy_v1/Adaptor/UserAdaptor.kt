package com.example.tripy_v1.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.tripy_v1.R
import com.example.tripy_v1.Models.User
import android.content.Intent
import com.example.tripy_v1.View.Home.DetailsActivity


class UserAdaptor(val ListUser: MutableList<User>):RecyclerView.Adapter<UserAdaptor.ViewHolder>(){

    inner class ViewHolder(val item:View):RecyclerView.ViewHolder(item){
        val tvHotelName:TextView? = itemView.findViewById(R.id.tvHotelName)
        val tvRating:TextView? = itemView.findViewById(R.id.tvRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.users_item,parent,false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.tvHotelName?.text = ListUser.get(position).name.toString()
            holder.tvRating?.text = ListUser.get(position).email.toString()
            holder.item.setOnClickListener {
                val intent = Intent(it.context, DetailsActivity::class.java)
                //intent.putExtra("item_id", item.id)
                it.context.startActivity(intent)
            }
    }

    override fun getItemCount(): Int {
        return ListUser.size
    }

}