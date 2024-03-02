package com.example.demoapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(val context:Context, val userList: List<MyDataItem>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    var onItemClick :((MyDataItem) -> Unit) ? =null
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var userId : TextView
        var title:TextView
        var body: TextView

        init {
            userId = itemView.findViewById(R.id.userId)
            title = itemView.findViewById(R.id.title)
            body = itemView.findViewById(R.id.body)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_items,parent, false)
        return ViewHolder((itemView))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userId.text = userList[position].userId.toString()
        holder.title.text = userList[position].title
        holder.body.text = userList[position].body

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(userList[position])
        }
    }
}