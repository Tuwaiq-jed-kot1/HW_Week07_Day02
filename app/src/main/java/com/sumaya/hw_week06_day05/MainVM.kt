package com.sumaya.hw_week06_day05

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwaiq.restandretrofit.data.network.FlickrRepo
import com.tuwaiq.restandretrofit.data.network.models.MoviesData
import kotlinx.coroutines.launch


class MainVM : ViewModel() {
    val repo = FlickrRepo()
    fun fetchIterestingList(searchKeyWord: String? = null): LiveData<MoviesData> {
        val movie = MutableLiveData<MoviesData>()
        viewModelScope.launch {
            try {
                if (searchKeyWord.isNullOrEmpty()) {
                    movie.postValue(repo.fetchMoviesList())

                } else {
                    movie.postValue(repo.searchMovies(searchKeyWord))
                }

            } catch (e: Throwable) {
                Log.e("Movies ", "videos  Problem: ${e.localizedMessage}")
            }
        }
        return movie
    }
}
