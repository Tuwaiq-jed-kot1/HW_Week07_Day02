package com.tuwaiq.restandretrofit.data.network.models



data class Results (
    val poster_path : String,
    val release_date : String,
    val title : String,
    val id : Int,
    val vote_average : Double
)

data class MoviesData (
    val results : List<Results>
)

