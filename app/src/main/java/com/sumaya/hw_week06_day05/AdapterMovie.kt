package com.sumaya.hw_week06_day05

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class AdapterMovie(val movieData:List<Results>): RecyclerView.Adapter<CustomHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return CustomHolder(view)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
val movie=movieData[position]
        holder.Title.text=movie.title
        holder.posterImage.load("https://image.tmdb.org/t/p/w500"+movie.poster_path)
        holder.date.text=movie.release_date
        holder.voteAverage.text= movie.vote_average.toString()
        holder.posterImage.setOnClickListener {
            val intent = Intent(holder.itemView.context,WebView::class.java)
            val URL_KEY="URL"
            intent.putExtra(URL_KEY,"https://www.themoviedb.org/movie/"+movie.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
      return movieData.size
    }
}

class CustomHolder (itemView: View):RecyclerView.ViewHolder(itemView){
   val  Title:TextView=itemView.findViewById(R.id.txtTitle)
   val posterImage:ImageView=itemView.findViewById(R.id.imagePoster)
    val voteAverage:TextView=itemView.findViewById(R.id.txtVote)
    val date:TextView=itemView.findViewById(R.id.txDate)
}
