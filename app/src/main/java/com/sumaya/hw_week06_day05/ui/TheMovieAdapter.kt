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
import com.sumaya.hw_week06_day05.URL_KEY
import com.sumaya.hw_week06_day05.data.models.Results

class TheMovieAdapter(private val theMovieDataList: List<Results>) :
    RecyclerView.Adapter<TheMovieAdapter.CustomHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_recyclerview_item, parent, false)
        return CustomHolder(view)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        val movieData = theMovieDataList[position]
        holder.txtTitle.text = movieData.title
        holder.txtDate.text = movieData.release_date
        holder.imageView.load("https://image.tmdb.org/t/p/w500/" + movieData.poster_path)
        holder.imageView.setOnClickListener {
        val intent = Intent(holder.itemView.context,WebViewActivity::class.java)
            intent.putExtra(URL_KEY,"https://www.themoviedb.org/movie/"+movieData.id)
            holder.itemView.context.startActivity(intent)
        }
        holder.txtVoteAverage.text = movieData.vote_average.toString()
    }

    override fun getItemCount(): Int {
        return theMovieDataList.size
    }

    class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitle: TextView = itemView.findViewById(R.id.tv_title)
        var txtDate: TextView = itemView.findViewById(R.id.tv_date)
        var imageView: ImageView = itemView.findViewById(R.id.iv_image)
        var txtVoteAverage: TextView = itemView.findViewById(R.id.tv_vote_average)
    }
}
