package com.example.playo.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.playo.R
import com.example.playo.activities.WebView
import com.example.playo.models.SortedApiData
import kotlinx.android.synthetic.main.view_holder.view.*

class RecyclerAdapter(context: Context, list: List<SortedApiData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val context: Context = context
    var list: List<SortedApiData> = list

    //inner class for the ViewHolder
    private inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.Photo)
        var author: TextView = itemView.findViewById(R.id.author)
        var title: TextView = itemView.findViewById(R.id.Title)
        var description: TextView = itemView.findViewById(R.id.description)

        fun bind(position: Int) {
            author.text = list[position].author
            title.text = list[position].title
            description.text = list[position].description
            Glide.with(context).load(list[position].photo).into(image)
        }

        fun click(position: Int) {
            itemView.Photo.setOnClickListener {

                // passing url to the WebView Activity
                val intent = Intent(context, WebView::class.java)
                val bundle = Bundle()
                bundle.putString("url",list[position].url)
                intent.putExtras(bundle)
                startActivity(context,intent,bundle)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.view_holder, parent, false)
        return UserViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserViewHolder).bind(position)
        holder.click(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}