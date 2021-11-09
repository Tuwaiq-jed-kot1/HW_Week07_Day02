package com.sumaya.hw_week06_day05.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumaya.hw_week06_day05.data.modules.MoviesData
import com.sumaya.hw_week06_day05.data.network.MoviesRepo
import kotlinx.coroutines.launch

class MainVM: ViewModel() {
    val repo = MoviesRepo()

    fun fetchInterestingList(searchKeyWord: String? = null): LiveData<MoviesData> {
        val photos = MutableLiveData<MoviesData>()
        viewModelScope.launch {
            try {
                if (searchKeyWord.isNullOrEmpty()) {
                    photos.postValue(repo.fetchList())
                }else{
                    photos.postValue(repo.searchPhotos(searchKeyWord))
                }
            }catch (e: Throwable){
                Log.e("Flickr Image","Flickr Images Problem ${e.localizedMessage}")
            }
        }
        return photos
    }
}