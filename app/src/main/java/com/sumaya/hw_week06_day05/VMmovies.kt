package com.sumaya.hw_week06_day05

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class VMmovies :ViewModel() {
    val repo= MovieRepo()
    fun getAllMovies(searchKeyWord:String? =null): LiveData<MovieRoot> {
        val movies= MutableLiveData<MovieRoot>()
        viewModelScope.launch {
            try {
                if (searchKeyWord.isNullOrEmpty()) {
                    movies.postValue(repo.getAllmovies())
                } else {
                    movies.postValue(repo.searchInMovies(searchKeyWord))
                }

            }catch (e : Throwable){
                Log.e("movies","movies${e.localizedMessage}")

            }

        }
        return movies
    }

}