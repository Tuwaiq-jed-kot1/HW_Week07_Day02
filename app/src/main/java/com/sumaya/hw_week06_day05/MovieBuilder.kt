package com.shady.restandretrofit.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object MovieBuilder {
    private const val BASE_URL = "https://www.themoviedb.org/"
    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val MovieAPI: MovieAPI = retrofit().create(com.shady.restandretrofit.data.network.MovieAPI::class.java)
}