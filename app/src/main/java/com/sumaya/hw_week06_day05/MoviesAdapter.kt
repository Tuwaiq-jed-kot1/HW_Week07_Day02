package com.sumaya.hw_week06_day05

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class MoviesAdapter(private val moviesData: List<Results>) : RecyclerView.Adapter<CustomHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_recyclerview_item, parent,false)
        return CustomHolder(view)

    }


    override fun onBindViewHolder(holder: CustomHolder, position: Int) {

        val movie= moviesData[position]

        holder.title.text="${movie.title}"
        holder.date.text=" ${movie.release_date}"
        holder.posterImage.load("https://image.tmdb.org/t/p/w500"+movie.poster_path)
        holder.voteAverage.text=" ${movie.vote_average}"
        holder.idTv= movie.id.toString()


    }

    override fun getItemCount(): Int {

        return moviesData.size
    }

}

class CustomHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener{

    val title : TextView = itemView.findViewById(R.id.tvTitle)
    val voteAverage :TextView = itemView.findViewById(R.id.tvVote)
    val date: TextView = itemView.findViewById(R.id.tvDate)
    val posterImage : ImageView = itemView.findViewById(R.id.ivImage)
    lateinit var idTv: String
    init{
        posterImage.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val i = Intent(v!!.context,MoviesWebView::class.java)
        i.putExtra("A",idTv)
        v.context.startActivity(i)
    }


}