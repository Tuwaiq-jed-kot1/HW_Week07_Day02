package com.sumaya.hw_week06_day05.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sumaya.hw_week06_day05.R
import com.sumaya.hw_week06_day05.Results

class TMDBAdapter(val movieData: List<Results>) : RecyclerView.Adapter<CustomHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tmdb_recycler_view,parent, false)
        return CustomHolder(view)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        val movie= movieData[position]
        holder.movieId = movie.id.toString()
        holder.titleTV.text = movie.title
        holder.voteTV.text = movie.vote_average.toString()
        holder.dateTV.text = movie.release_date
        holder.posterIV.load("https://image.tmdb.org/t/p/w500"+movie.poster_path)
        holder.posterIV.setOnClickListener{
            val i = Intent(holder.posterIV.context,
                MovieWebView::class.java).apply {
                putExtra("show",holder.movieId)
            }
            holder.itemView.context.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
        return movieData.size
    }

}

class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView)/*, View.OnClickListener*/ {
    val titleTV: TextView= itemView.findViewById(R.id.movieTitleTV)
    val voteTV: TextView= itemView.findViewById(R.id.averageVotesTV)
    val dateTV: TextView= itemView.findViewById(R.id.releaseDateTV)
    val posterIV: ImageView= itemView.findViewById(R.id.movieImageIV)
    lateinit var movieId: String
}