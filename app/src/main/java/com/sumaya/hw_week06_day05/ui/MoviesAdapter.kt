package com.sumaya.hw_week06_day05.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sumaya.hw_week06_day05.R
import com.sumaya.hw_week06_day05.WebViewActivity
import com.sumaya.hw_week06_day05.data.modules.Result

class MoviesAdapter(val moviesData: List<Result>) : RecyclerView.Adapter<CustomHolder>() {
    private lateinit var webView: WebView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return CustomHolder(view)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        val movie = moviesData[position]
        holder.titleTV.text=movie.title.toString()
        holder.dateTV.text=movie.release_date.toString()
        holder.votesTV.text=movie.vote_average.toString()
        holder.imageIV.load("https://image.tmdb.org/t/p/original/"+movie.poster_path){
        }

        holder.itemView.setOnClickListener{
            val intent= Intent( holder.itemView.context, WebViewActivity::class.java).apply {
                putExtra("webKey",movie.id.toString())
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
    //val IdTV: TextView = itemView.findViewById(R.id.tvID)
}
