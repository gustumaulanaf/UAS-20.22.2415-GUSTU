package com.gustu.github.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gustu.github.R
import com.gustu.github.data.model.SearchResponse
import com.gustu.github.ui.DetailActivity
import kotlinx.android.synthetic.main.item_users.view.*

class UsersAdapter(val list: SearchResponse, val context: Context) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.imgUsers
        val name = itemView.tvUsername
        val type = itemView.tvType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list.items?.get(position)
        Glide.with(context).load(item?.avatarUrl).into(holder.img)
        holder.type.setText(item?.type)
        holder.name.setText(item?.login)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("username", item?.login)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.items?.size ?: 0
    }
}