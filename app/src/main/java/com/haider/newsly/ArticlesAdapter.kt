package com.haider.newsly

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ArticlesAdapter(private val articles: List<Article>, private val context: Context,private val listener: Listener): RecyclerView.Adapter<ArticlesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view_newsly,parent,false)
        return ArticlesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {

        val obj = articles[position]
        holder.image?.let {
            Glide.with(context)
                .load(articles[position].urlToImage)
                .into(holder.image)
        }

        holder.title.text = articles[position].title

        holder.description.text = articles[position].description

        holder.itemView.setOnClickListener {
            listener.onItemClicked(obj)
        }

    }

    override fun getItemCount(): Int {
        return articles.size
    }
}

class ArticlesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val image = itemView.findViewById<ImageView>(R.id.image)
    val title = itemView.findViewById<TextView>(R.id.title)
    val description = itemView.findViewById<TextView>(R.id.description)
}