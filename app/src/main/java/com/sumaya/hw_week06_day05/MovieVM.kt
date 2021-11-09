package com.sumaya.hw_week06_day05.Data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieVM :ViewModel() {
val repo =MovieRepo()
    fun fetchIntrestingList(searchKeyword:String? = null): LiveData<Data>{
        var Movie1 =MutableLiveData<Data>()
        viewModelScope.launch {
           try {
                if (searchKeyword.isNullOrEmpty()){
                   Movie1.postValue(repo.fetchIntrestingList())
                }else{
                   Movie1.postValue(repo.seachMovie(searchKeyword))
                }
           }catch (e:Throwable){
              Log.e("Movie Image", "problem: ${e.localizedMessage}")
           }

        }
        return Movie1
    }
}