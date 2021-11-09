package com.sumaya.hw_week06_day05.network

import com.sumaya.hw_week06_day05.modle.DbData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoDB {
    private val api = DbBuilder.DbApi

    suspend fun viewMoviesInfo(): DbData = withContext(Dispatchers.IO) {
        api.viewMoviesInfo()
    }

    suspend fun searchMovies(searchKeyWord: String): DbData = withContext(Dispatchers.IO) {
        api.searchMovies(searchKeyWord)
    }
}