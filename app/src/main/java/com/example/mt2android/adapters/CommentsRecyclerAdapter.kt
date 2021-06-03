package com.example.mt2android.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mt2android.R
import com.example.mt2android.api.Comment


class CommentsRecyclerAdapter (private val list: List<Comment>): RecyclerView.Adapter<CommentsRecyclerAdapter.RecyclerViewHolder>() {


    class RecyclerViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val commentName: TextView = itemView.findViewById(R.id.commentName)
        val commentEmail: TextView = itemView.findViewById(R.id.commentEmail)
        val commentBody: TextView = itemView.findViewById(R.id.commentBody)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_comments, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        holder.commentName.text = list[position].name
        holder.commentEmail.text = list[position].email
        holder.commentBody.text = list[position].body
    }

}