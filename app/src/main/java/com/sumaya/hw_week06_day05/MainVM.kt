package com.sumaya.hw_week06_day05

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainVM: ViewModel() {

    val repo= MoviesRepo()
    fun fetchMovies(searchKeyword: String?): LiveData<MoviesData> {
        val movies = MutableLiveData<MoviesData>()
        viewModelScope.launch {
            try {
                if (searchKeyword.isNullOrBlank()){
                    movies.postValue(repo.fetchMovies())
                } else{
                    movies.postValue(repo.searchMovies(searchKeyword))
                }

            } catch (e: Throwable){
                Log.e("Movies","Movies Problem: ${e.localizedMessage}")
            }
        }
        return movies
    }
}