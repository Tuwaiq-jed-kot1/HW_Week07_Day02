package com.sumaya.hw_week06_day05.ui

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
import com.sumaya.hw_week06_day05.R

class MainActivity : AppCompatActivity() {

    private lateinit var movieRV: RecyclerView
    private lateinit var sharedPreferences: SharedPreferences
    private val vm by lazy {
        ViewModelProvider(this).get(MainVM::class.java)
    }
    private val SHARED_KEY = "lastSearch"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = this.getSharedPreferences("movieSharePreference",Context.MODE_PRIVATE)

        movieRV = findViewById(R.id.rvMovie)
        movieRV.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )//GridLayoutManager(this, 2)

        loadMovieImages()
    }

    private fun loadMovieImages(query: String? = null) {
        vm.fetchInterestingList(query).observe(this, {
            if (query.isNullOrEmpty()) {
                movieRV.adapter = MovieAdapter(it.results)

            } else {
                movieRV.swapAdapter(MovieAdapter(it.results), false)
            }
            Log.d("Movie Main Response", it.results.toString())
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchIcon: MenuItem = menu!!.findItem(R.id.app_bar_search)
        val TAG = "searchView"
        val searchView = searchIcon.actionView as SearchView
        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d(TAG, "Query text submit: $query")
                    val sharedPref =
                        sharedPreferences.getString(SHARED_KEY, "This was your 1st search!! ")
                    sharedPreferences
                        .edit()
                        .putString(SHARED_KEY, "Your last search was: $query")
                        .apply()
                    Toast.makeText(this@MainActivity, sharedPref, Toast.LENGTH_LONG).show()
                    loadMovieImages(query?.trim())
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.d(TAG, "QueryTextChange: $newText")
                    return false
                }

            })
        }

        return super.onCreateOptionsMenu(menu)
    }
}