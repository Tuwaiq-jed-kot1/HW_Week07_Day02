package com.sumaya.hw_week06_day05.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumaya.hw_week06_day05.TheMovieDBData

import com.sumaya.hw_week06_day05.data.network.TMDBRepo
import kotlinx.coroutines.launch

class MainVM : ViewModel() {
    val repo = TMDBRepo()
    fun fetchInterestingList(searchKeyWord: String? = null): LiveData<TheMovieDBData> {
        val movies = MutableLiveData<TheMovieDBData>()
        viewModelScope.launch {
            try {
                if (searchKeyWord.isNullOrBlank()) {
                    movies.postValue(repo.fetchInterestingList())
                } else {
                    movies.postValue(repo.searchPhotos(searchKeyWord))
                }

            } catch (e: Throwable) {
                Log.e("TMDB Movie", "TMDB Movie Problem: ${e.localizedMessage}")
            }
        }
        return movies
    }
}