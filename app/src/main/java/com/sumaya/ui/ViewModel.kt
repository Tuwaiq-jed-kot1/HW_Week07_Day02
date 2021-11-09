package com.sumaya.hw_week06_day05.ui

import MoviesDB
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumaya.hw_week06_day05.data.network.Repo
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {

    val repo = Repo()
    fun fetchMovies(searchKey: String? = null): LiveData<MoviesDB> {
        val movies = MutableLiveData<MoviesDB>()
        viewModelScope.launch {
            try {
                if (searchKey.isNullOrEmpty()) {
                    movies.postValue(repo.fetchMovies())
                } else {

                    movies.postValue(repo.searchMovie(searchKey.trim()))

                }
            } catch (e: Throwable) {
                Log.e("Movies", "Problem: ${e.localizedMessage}")

            }
        }
        return movies
    }
}