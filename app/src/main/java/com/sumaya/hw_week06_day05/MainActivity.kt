package com.sumaya.hw_week06_day05

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var flickrRV: RecyclerView
    private val vm by lazy {
        ViewModelProvider(this).get(MainVM::class.java)
    }
    private lateinit var sharePreferences: SharedPreferences
    private val SHARED_KEY = "lastSearch"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharePreferences = this.getSharedPreferences(" The Movie DB Search Preference", Context.MODE_PRIVATE)
        flickrRV = findViewById(R.id.rvFlickr)
        flickrRV.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        loadMovie()
    }

@SuppressLint("LongLogTag")
private fun loadMovie(query: String? = null) {
    vm.fetchIterestingList(query).observe(this, {

        if (query.isNullOrEmpty()) {

            flickrRV.adapter = FlickrAdapter(it.results)

        }else {
            //update after search
            flickrRV.swapAdapter(FlickrAdapter(it.results), false)
        }
        Log.d(" The Movie DB main Response", it.results.toString())
    })
}

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)
        val TAG = "searchView"
        val searchIcon: MenuItem = menu!!.findItem(R.id.app_bar_search)
        val searchView = searchIcon.actionView as SearchView

        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d(TAG,"QueryTextSubmit: $query")
                    val sharePref = sharePreferences.getString(SHARED_KEY,"This was your first search!! yay!!")
                    Toast.makeText(context,sharePref, Toast.LENGTH_LONG).show()
                    sharePreferences
                        .edit()
                        .putString(SHARED_KEY,"your last search was: $query")
                        .apply()
                    loadMovie(query?.trim())
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.d(TAG,"QueryTextChange: $newText")
                    return false
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }
}