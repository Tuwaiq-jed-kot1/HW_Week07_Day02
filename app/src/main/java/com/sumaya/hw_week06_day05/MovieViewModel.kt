package com.sumaya.hw_week06_day05


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {

    val repo = MovieRepo()
    fun fetchMovies(searchKey: String? = null): LiveData<MoviesDB> {
        val movies = MutableLiveData<MoviesDB>()
        viewModelScope.launch {
            try {
                if (searchKey.isNullOrEmpty()) {
                    movies.postValue(repo.fetchMovie())
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