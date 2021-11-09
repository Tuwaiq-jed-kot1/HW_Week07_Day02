package com.sumaya.hw_week06_day05.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumaya.hw_week06_day05.data.model.Data
import com.sumaya.hw_week06_day05.data.model.Rate
import com.sumaya.hw_week06_day05.data.network.TMDBRepo
import kotlinx.coroutines.launch

class MainVM : ViewModel() {
    val repo = TMDBRepo()
    val TAG="vm movies exception"
    fun getMovies(searchKeyword: String? = null): LiveData<Data> {
        val movies = MutableLiveData<Data>()
        viewModelScope.launch {
            try {
                if (searchKeyword.isNullOrEmpty()) {
                    movies.postValue(repo.getMovies())
                } else {
                    movies.postValue(repo.searchMovies(searchKeyword))
                }
            } catch (t: Throwable) {
                Log.e(TAG, t.localizedMessage)
            }
        }
        return movies
    }
    fun rateMovie(value: Double, movie_id: Int): LiveData<Rate>{
        val statusMessage = MutableLiveData<Rate>()
        viewModelScope.launch {
            try {
                statusMessage.postValue(repo.rateMovie(value, movie_id))
            }catch (t:Throwable){
                Log.e(TAG, t.localizedMessage)
            }
        }
        return statusMessage
    }

    fun deleteRateMovie(movie_id: Int): LiveData<Rate>{
        val statusMessage = MutableLiveData<Rate>()
        viewModelScope.launch {
            try {
                statusMessage.postValue(repo.deleteRateMovie(movie_id))
            }catch (t:Throwable){
                Log.e(TAG, t.localizedMessage)
            }
        }
        return statusMessage
    }
}