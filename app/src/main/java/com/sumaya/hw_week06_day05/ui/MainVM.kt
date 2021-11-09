package com.sumaya.hw_week06_day05.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumaya.hw_week06_day05.network.MovieRepo
import com.sumaya.hw_week06_day05.network.models.MovieData
import kotlinx.coroutines.launch

class MainVM: ViewModel() {

    val repo = MovieRepo()
    fun fetchInterestingList(searchKeyWord: String?): LiveData<MovieData>{
        val movies = MutableLiveData<MovieData>()
        viewModelScope.launch {
            try {
                if (searchKeyWord.isNullOrBlank()){
                    movies.postValue(repo.fetchInterestingList())
                } else{
                    movies.postValue(repo.searchPhotos(searchKeyWord))
                }

            } catch (e: Throwable){
                Log.e("Movie data","Movie data Problem: ${e.localizedMessage}")
            }
        }
        return movies
    }

}

