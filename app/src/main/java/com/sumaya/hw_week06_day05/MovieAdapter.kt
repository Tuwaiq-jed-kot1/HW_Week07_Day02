package com.sumaya.hw_week06_day05.Data

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sumaya.hw_week06_day05.R
import com.sumaya.hw_week06_day05.webView
import java.util.zip.Inflater

class MovieAdapter(val Movies :List<Results>) :RecyclerView.Adapter<MovieAdapter.CustomHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_items,parent,false)
        return CustomHolder(view)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
         val movie =Movies[position]
        holder.ImageView.load("https://image.tmdb.org/t/p/w500"+movie.poster_path)
        holder.ReleaseData.text=movie.release_date
        holder.Title.text=movie.title
        holder.vote.text= movie.vote_average.toString()
        //holder.info.load("https://www.themoviedb.org/movie/"+movie.id)


        val intent = Intent(holder.itemView.context,webView::class.java)
        intent.putExtra("M",movie.id)
        holder.itemView.context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return Movies.size
    }

    class CustomHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
      val  Title:TextView = itemView.findViewById(R.id.Title)
        val ReleaseData:TextView=itemView.findViewById(R.id.ReleaseDate)
        val vote:TextView=itemView.findViewById(R.id.Vote)
        val ImageView:ImageView=itemView.findViewById(R.id.ivMovie)
        val info :ImageView = itemView.findViewById(R.id.info)

    }

}