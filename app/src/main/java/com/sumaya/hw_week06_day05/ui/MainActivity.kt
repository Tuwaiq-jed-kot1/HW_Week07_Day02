package com.sumaya.hw_week06_day05.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sumaya.hw_week06_day05.R

class MainActivity : AppCompatActivity() {
    private lateinit var theMovieRecyclerView: RecyclerView
    private val mainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences =
            this.getSharedPreferences("TheMovieSearchSharedPreference", Context.MODE_PRIVATE)
        theMovieRecyclerView = findViewById(R.id.rvTheMovies)
        theMovieRecyclerView.layoutManager = LinearLayoutManager(this)
        loadTheMovies()

    }

    private fun loadTheMovies(query: String? = null) {
        mainViewModel.fetchMoviesList(query).observe(this) {
            if (query.isNullOrEmpty()) {
                theMovieRecyclerView.adapter = TheMovieAdapter(it.results)
            } else {
                theMovieRecyclerView.swapAdapter(TheMovieAdapter(it.results), false)
            }
            Log.d("TheMovie main response", it.results.toString())
        }
    }

    private lateinit var sharedPreferences: SharedPreferences
    private var SHARED_KEY = "lastSearch"

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val TAG = "searchView"
        val searchIcon: MenuItem = menu!!.findItem(R.id.app_bar_search)
        val searchView = searchIcon.actionView as SearchView
        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d(TAG, "Query Text Submit : $query")
                    val sharedPref =
                        sharedPreferences.getString(SHARED_KEY, "this was your 1st Search!! Yay!!")
                    Toast.makeText(context, sharedPref, Toast.LENGTH_SHORT).show()
                    sharedPreferences.edit()
                        .putString(SHARED_KEY, "Your last search was : $query")
                        .apply()
                    loadTheMovies(query?.trim())
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.d(TAG, "Query Text Changed : $newText")
                    return false
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }
}