package com.sumaya.hw_week06_day05.data.network



import com.sumaya.hw_week06_day05.TheMovieDBData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TMDBRepo {
    private val api = TMDBBuilder.TMDBAPI

    suspend fun fetchInterestingList(): TheMovieDBData = withContext(Dispatchers.IO) {
        api.fetchPhotos()
    }

    suspend fun searchPhotos(searchKeyWork: String): TheMovieDBData = withContext(Dispatchers.IO) {
        api.searchPhotos(searchKeyWork)
    }
}