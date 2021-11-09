package com.sumaya.hw_week06_day05
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class MoviesAdapter(val MoviesData: List<MovieData>) : RecyclerView.Adapter<CustomHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return CustomHolder(view)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        //The movie information is Title, poster image, vote average, and date.
        val movie = MoviesData[position]
        holder.posterIV.load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
        holder.titleTV.text = movie.title
        holder.idTV = movie.id.toString()
        holder.voteTV.text = movie.vote_average.toString()
        holder.dateTV.text = movie.release_date

    }

    override fun getItemCount(): Int {
        return MoviesData.size
    }

}

class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleTV: TextView = itemView.findViewById(R.id.tvTitle)
    val voteTV: TextView = itemView.findViewById(R.id.tvVote)
    val dateTV: TextView = itemView.findViewById(R.id.tvDate)
    val posterIV: ImageView = itemView.findViewById(R.id.ivPoster)
}