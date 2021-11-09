package com.sumaya.hw_week06_day05.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumaya.hw_week06_day05.data.models.TmdbListMovies
import com.sumaya.hw_week06_day05.data.network.TmdbRepo
import kotlinx.coroutines.launch

class MainVM: ViewModel() {
    val repo = TmdbRepo()

    fun fetchList(searchKeyword: String? = null): LiveData<TmdbListMovies> {
        val movies = MutableLiveData<TmdbListMovies>()
        viewModelScope.launch {
            try {
                if ( searchKeyword.isNullOrEmpty()){
                   movies.postValue(repo.fetchList())
                } else {
                    movies.postValue(repo.searchMovies(searchKeyword))

                }
            } catch (e: Throwable) {
                Log.e("Tmdb Movie","Tmdb Movie Problem: ${e.localizedMessage}")
            }
        }
        return movies
    }
}