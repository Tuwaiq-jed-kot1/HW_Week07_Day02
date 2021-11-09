package com.sumaya.hw_week06_day05.Data

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDBApi {

   @GET("?api_key=acb7da2411d1b42462e0fb514457e32b&language=en-US&page=1")
   suspend fun fetchMovie():Data

   @GET("\n" +
           "?api_key=acb7da2411d1b42462e0fb514457e32b&language=en-US&page=1&include_adult=false")
   suspend fun seachMovie(@Query("query") searchKeyword: String):Data
   @GET("https://api.themoviedb.org/3/find/{external_id}?api_key=acb7da2411d1b42462e0fb514457e32b&language=en-US&external_source=imdb_id")
   suspend fun movieId():Data
}