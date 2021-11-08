package com.tuwaiq.restandretrofit.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object FlickerBuilder {

   private const val BASE_URL = "https://api.themoviedb.org/3/"

    private fun retrofit() :Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val flickrAPI: FlickrAPI = retrofit().create(FlickrAPI::class.java)

}