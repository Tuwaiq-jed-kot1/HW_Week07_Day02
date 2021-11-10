package com.sumaya.hw_week06_day05

data class TheMovieDBData (
    val results : List<Results>
)
data class Results (
    val id : Int,
    val poster_path : String,
    val release_date : String,
    val title : String,
    val vote_average : Double,
)