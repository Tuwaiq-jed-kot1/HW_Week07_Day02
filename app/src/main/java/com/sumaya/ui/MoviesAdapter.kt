package com.sumaya.hw_week06_day05.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sumaya.hw_week06_day05.R
import com.sumaya.hw_week06_day05.data.models.Movie

class MoviesAdapter(val MoviesData: List<Movie>) : RecyclerView.Adapter<CustomHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return CustomHolder(view)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {

        //The movie information is Title, poster image, vote average, and date.
        val movie = MoviesData[position]
        holder.posterIV.load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
        holder.titleTV.text = movie.title
        holder.voteTV.text = movie.vote_average.toString()
        holder.dateTV.text = movie.release_date
        holder.posterIV.setOnClickListener {
            holder.linearLay1.visibility = View.GONE
            holder.webView.visibility = View.VISIBLE
            holder.webView.webViewClient = WebViewClient()
            holder.webView.settings.builtInZoomControls = true

            holder.webView.loadUrl("https://www.themoviedb.org/movie/${movie.id}")
            holder.cancelButton.visibility = View.VISIBLE
        }
        holder.cancelButton.setOnClickListener {
            holder.linearLay1.visibility = View.VISIBLE
            holder.webView.visibility = View.GONE
            holder.cancelButton.visibility = View.GONE


        }

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
    val linearLay1: LinearLayout = itemView.findViewById(R.id.linearLay1)
    val webView: WebView = itemView.findViewById(R.id.webV)
    val cancelButton: ImageButton = itemView.findViewById(R.id.cancel_button)
}
