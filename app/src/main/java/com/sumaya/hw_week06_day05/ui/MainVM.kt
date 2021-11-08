package com.sumaya.hw_week06_day05.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumaya.hw_week06_day05.MoviesData
import com.sumaya.hw_week06_day05.network.MovieRepo
import kotlinx.coroutines.launch

class MainVM : ViewModel() {
    val repo = MovieRepo()
    fun fetchInterestingList(searchKeyword: String?): LiveData<MoviesData> {
        val movies = MutableLiveData<MoviesData>()
        viewModelScope.launch {
            try {
                if (searchKeyword.isNullOrEmpty()){
                    movies.postValue(repo.fetchInterestingList())
                } else {
                    movies.postValue(repo.searchMovie(searchKeyword))
                }
            } catch (e: Throwable) {
                Log.e("Movies Image","Movies Image Problem: ${e.localizedMessage}")
            }
        }
        return movies
    }
}