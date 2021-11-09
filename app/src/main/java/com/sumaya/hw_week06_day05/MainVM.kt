package com.sumaya.hw_week06_day05

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shady.restandretrofit.data.network.MovieRepo
import kotlinx.coroutines.launch

class MainVM: ViewModel() {
    val repo = MovieRepo()
    fun fetchInterestingList(searchKeyword: String?): LiveData<JsonData>{
        val photos = MutableLiveData<JsonData>()
            viewModelScope.launch {
                try {
                    if (searchKeyword.isNullOrEmpty()){
                        photos.postValue(repo.fetchInterestingList())
                    } else {
                        photos.postValue(repo.searchMovie(searchKeyword))
                    }
                } catch (e: Throwable) {
                    Log.e("Movie Image","Movie Image Problem: ${e.localizedMessage}")
                }
            }
        return photos
    }
}