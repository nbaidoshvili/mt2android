package com.example.mt2android

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class RecyclerViewAdapter (private val list: List<Post>): RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {


    class RecyclerViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val title: TextView = itemView.findViewById(R.id.postTitle)
        val body: TextView = itemView.findViewById(R.id.postBody)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_posts, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        holder.title.text = list[position].title
        holder.body.text = list[position].body
    }

}