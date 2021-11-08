package com.sumaya.hw_week06_day05


data class Results (

    val poster_path : String,
    val release_date : String,
    val title : String,
    val vote_average : Double,
    val id : Int,
)
data class MoviesData (

    val results : List<Results>,
)