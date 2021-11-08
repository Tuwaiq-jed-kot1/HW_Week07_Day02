package com.tuwaiq.restandretrofit.data.network

import com.tuwaiq.restandretrofit.data.network.models.MoviesData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class FlickrRepo {

    private val api = FlickerBuilder.flickrAPI

    suspend fun fetchMoviesList(): MoviesData = withContext(Dispatchers.IO) {
        api.fetchMovies()
    }

    suspend fun searchMovies(searchKeyWord:String): MoviesData = withContext(Dispatchers.IO) {
        api.searchMovies(searchKeyWord )
    }
}
