package com.sumaya.hw_week06_day05.data.models


data class TmdbListMovies (
    val results: List<TmdbMovie>
        )

data class TmdbMovie (
    val poster_path: String,
    val release_date : String,
    val title : String,
    val vote_average : Double,
    val id : Int
)


