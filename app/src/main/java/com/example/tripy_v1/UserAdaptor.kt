package com.example.tripy_v1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.tripy_v1.models.User


class UserAdaptor(val ListUser: MutableList<User>):RecyclerView.Adapter<UserAdaptor.ViewHolder>(){

    inner class ViewHolder(item:View):RecyclerView.ViewHolder(item){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.users_item,parent,false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            val tvName:TextView? = findViewById(R.id.tvName)
            val tvEmail:TextView? = findViewById(R.id.tvEmail)


            tvName?.text = ListUser.get(position).name.toString()
            tvEmail?.text = ListUser.get(position).email.toString()

        }
    }
    override fun getItemCount(): Int {
        return ListUser.size
    }

}