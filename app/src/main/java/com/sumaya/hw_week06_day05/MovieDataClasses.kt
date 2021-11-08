package com.sumaya.hw_week06_day05

data class movie (
     var id : Int,
     var poster_path : String,
     var release_date : String,
     var title : String,
     var vote_average : Double,
)


data class MovieRoot (
   var results : List<movie>
)