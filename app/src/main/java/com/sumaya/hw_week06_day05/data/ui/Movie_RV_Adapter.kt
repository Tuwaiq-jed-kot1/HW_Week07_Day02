package com.sumaya.hw_week06_day05.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sumaya.hw_week06_day05.R
import com.sumaya.hw_week06_day05.data.models.Results

class Movie_RV_Adapter(val videosData: List<Results>,private val activity: MainActivity) : RecyclerView.Adapter<CustomHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_item, parent, false)
        return CustomHolder(view)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        val movie = videosData[position]
        holder.movieTitle.text = movie.title
        holder.movieDate.text = movie.release_date
        holder.movieRate.text = movie.vote_average.toString()
        holder.moviePoster.load("https://image.tmdb.org/t/p/w500"+movie.poster_path)
        holder.moviePoster.setOnClickListener{
            activity.navigateToWebViewFragment(movie.id)
        }

    }

    override fun getItemCount(): Int {
        return videosData.size
    }

}

class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val movieTitle:TextView = itemView.findViewById(R.id.MovieTitle)
    val movieDate:TextView = itemView.findViewById(R.id.MovieDate)
    val movieRate:TextView = itemView.findViewById(R.id.MovieRate)
    val moviePoster:ImageView = itemView.findViewById(R.id.MoviePoster)
}
