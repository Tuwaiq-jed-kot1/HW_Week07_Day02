package com.sumaya.hw_week06_day05.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumaya.hw_week06_day05.data.models.TheMovieDBData
import com.sumaya.hw_week06_day05.data.network.TheMovieDBRepo
import kotlinx.coroutines.launch

class MainVM:ViewModel() {
    val repo = TheMovieDBRepo()
    fun fetchInterestingList(searchKeyword: String?): LiveData<TheMovieDBData> {
        val movies = MutableLiveData<TheMovieDBData>()
        viewModelScope.launch {
            try {
                if (searchKeyword.isNullOrEmpty()){
                    movies.postValue(repo.fetchInterestingList())
                } else {
                    movies.postValue(repo.searchMovies(searchKeyword))
                }
            } catch (e: Throwable) {
                Log.e("The Movie DB data","The Movie DB data Problem: ${e.localizedMessage}")
            }
        }
        return movies
    }
}