package com.sumaya.hw_week06_day05
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sumaya.hw_week06_day05.WebViewActivity

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale

class MovieAdapter(private val movies: List<movie>) : RecyclerView.Adapter<CustomHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return CustomHolder(view)

    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        val movie =movies[position]
        holder.title.text=movie.title.toString()
        holder.date.text=movie.release_date.toString()
        holder.vote_average.text=movie.vote_average.toString()
        holder.Image.load("https://image.tmdb.org/t/p/original/"+movie.poster_path){
            scale(Scale.FILL)
        }

        holder.itemView.setOnClickListener{
            val intent= Intent( holder.itemView.context, WebViewActivity::class.java).apply {
              putExtra("webKey",movie.id.toString())
            }
            holder.itemView.context.startActivity(intent)


        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}

class CustomHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title)
    val date: TextView = itemView.findViewById(R.id.date)
    val vote_average: TextView = itemView.findViewById(R.id.vote_average)
    val Image: ImageView = itemView.findViewById(R.id.imageView)

}