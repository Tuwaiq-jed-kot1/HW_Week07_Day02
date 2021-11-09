package com.sumaya.hw_week06_day05

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
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {
    private lateinit var movieRV: RecyclerView
    private val vm by lazy {
        ViewModelProvider(this).get(MainVM::class.java)
    }
    private lateinit var sharedPreferences: SharedPreferences
    private val SHARED_KEY="lastSearch"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieRV = findViewById(R.id.rvMovie)
        movieRV.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)  //GridLayoutManager(this, 2)
        sharedPreferences = this.getSharedPreferences("flickrSharePreference", Context.MODE_PRIVATE)
        loadFlickrImages()
    }

    private fun loadFlickrImages(query: String? = null) {
        vm.fetchInterestingList(query).observe(this, {
            if(query.isNullOrEmpty()){
                movieRV.adapter = AdapterMovie(it.results)

            } else {
                movieRV.swapAdapter(AdapterMovie(it.results), false)
            }
            Log.d("Movie Main Response", it.results.toString())
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val TAG = "searchView"
        val searchIcon: MenuItem =  menu!!.findItem(R.id.app_bar_search)
        val searchView = searchIcon.actionView as SearchView
        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d(TAG, "QueryTextSubmit: $query")

                    val sharedPref =
                        sharedPreferences.getString(SHARED_KEY, "This was your 1st search!! ")
                    sharedPreferences
                        .edit()
                        .putString(SHARED_KEY, "Your last search was: $query")
                        .apply()
                    Toast.makeText(this@MainActivity, sharedPref, Toast.LENGTH_LONG).show()
                    loadFlickrImages(query?.trim())
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.d(TAG, "QueryTextSubmit: $newText")
                    return false
                }


            })
        }

        return super.onCreateOptionsMenu(menu)
    }

}