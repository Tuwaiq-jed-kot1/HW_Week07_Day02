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
import com.sumaya.hw_week06_day05.modle.Results

class DbAdapter(val Movies: List<Results>) : RecyclerView.Adapter<customHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): customHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_movie_item, parent, false)

        return customHolder(view)
    }

    override fun onBindViewHolder(holder: customHolder, position: Int) {
        val movie = Movies[position]
        holder.Title.text = movie.title
        holder.Date.text = movie.release_date
        holder.imageIV.load("https://image.tmdb.org/t/p/w500/" + movie.poster_path)
        holder.VoteAverage.text = movie.vote_average.toString()
        holder.idTv= movie.id.toString()
    }

    override fun getItemCount(): Int {
        return Movies.size
    }
}

class customHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
    val Title: TextView = itemView.findViewById(R.id.title_movie)
    val imageIV: ImageView = itemView.findViewById(R.id.poster)
    val Date: TextView = itemView.findViewById(R.id.date)
    val VoteAverage: TextView = itemView.findViewById(R.id.vote_average)
    lateinit var idTv: String

    init {
       imageIV.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val i = Intent(view?.context, DBWebview::class.java)
        i.putExtra("key", idTv)
        view?.context?.startActivity(i)
    }



}

