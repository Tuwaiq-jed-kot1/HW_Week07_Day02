package com.sumaya.hw_week06_day05.ui

import android.content.Intent
import android.icu.number.Scale
import android.view.Gravity.FILL
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout.FILL
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sumaya.hw_week06_day05.R
import com.sumaya.hw_week06_day05.data.modules.Result
import com.sumaya.hw_week06_day05.data.network.MoviesWebView


class MoviesAdapter( val moviesData: List<Result>) : RecyclerView.Adapter<CustomHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return CustomHolder(view)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        val movies = moviesData[position]
        holder.titleTV.text = movies.title
        holder.dateTV.text = movies.release_date
        holder.votesTV.text = movies.vote_average.toString()
        holder.imageIV.load("https://image.tmdb.org/t/p/original/"+ movies.poster_path)

        holder.imageIV.setOnClickListener{
            val intent= Intent( holder.itemView.context, MoviesWebView::class.java).apply {
                putExtra("mKey",movies.id.toString())

            }
            holder.itemView.context.startActivity(intent)


        }
    }

    override fun getItemCount(): Int {
        return moviesData.size
    }
}

class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleTV: TextView = itemView.findViewById(R.id.tvTitle)
    val dateTV: TextView = itemView.findViewById(R.id.tvDate)
    val votesTV: TextView = itemView.findViewById(R.id.tvVotes)
    val imageIV: ImageView = itemView.findViewById(R.id.ivPoster)


}