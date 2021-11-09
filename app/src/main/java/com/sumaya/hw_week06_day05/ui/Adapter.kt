package com.sumaya.hw_week06_day05.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sumaya.hw_week06_day05.R
import com.sumaya.hw_week06_day05.data.model.Results

class Adapter(private val movies: List<Results>, private val mainVM: MainVM) :
    RecyclerView.Adapter<CustomHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_item, parent, false)
        return CustomHolder(view)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.title
        holder.releaseDate.text = movie.release_date
        holder.vote.text = movie.vote_average.toString()
        holder.poster.load("https://image.tmdb.org/t/p/w500" + movie.poster_path)

        holder.rate.setOnClickListener {
            mainVM.rateMovie(10.0, movie.id).observeForever {
                Toast.makeText(
                    holder.itemView.context,
                    "${it.status_code} ${it.status_message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        holder.delete.setOnClickListener {
            mainVM.deleteRateMovie(movie.id).observeForever {
                Toast.makeText(
                    holder.itemView.context,
                    "${it.status_code} ${it.status_message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        holder.poster.setOnClickListener {
            val intent = Intent(holder.itemView.context,WebView::class.java)
            intent.putExtra(MOVIE_ID,movie.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}

class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var poster = itemView.findViewById<ImageView>(R.id.poster)
    var title = itemView.findViewById<TextView>(R.id.title)
    var releaseDate = itemView.findViewById<TextView>(R.id.release_date)
    var vote = itemView.findViewById<TextView>(R.id.vote_average)
    var rate = itemView.findViewById<ImageButton>(R.id.rate)
    var delete = itemView.findViewById<ImageButton>(R.id.delete)

}
