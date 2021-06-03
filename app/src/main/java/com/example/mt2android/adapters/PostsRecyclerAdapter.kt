package com.example.mt2android.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.mt2android.CommentsActivity
import com.example.mt2android.api.Post
import com.example.mt2android.R


class PostsRecyclerAdapter (private val list: List<Post>, private val c : Context): RecyclerView.Adapter<PostsRecyclerAdapter.RecyclerViewHolder>() {


    class RecyclerViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val title: TextView = itemView.findViewById(R.id.postTitle)
        val body: TextView = itemView.findViewById(R.id.postBody)
        val post: ConstraintLayout = itemView.findViewById(R.id.post)

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

        holder.post.setOnClickListener {

            val intent = Intent(c, CommentsActivity::class.java)
            intent.putExtra("POST", list[position])
            c.startActivity(intent)

        }
    }

}