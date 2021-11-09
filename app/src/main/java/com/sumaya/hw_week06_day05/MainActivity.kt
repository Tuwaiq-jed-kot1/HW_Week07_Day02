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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var sharedPreferences: SharedPreferences
    private val SHARED_PREF_KEY = "last_search"
    private val mainVM by lazy {
        ViewModelProvider(this).get(MainVM::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.movieRV)
        recyclerView.layoutManager = LinearLayoutManager(this)

        sharedPreferences = this.getSharedPreferences("last_search", Context.MODE_PRIVATE)

        loadMovies()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val TAG = "searchView"
        val searchIcon: MenuItem = menu!!.findItem(R.id.app_bar_search)
        val searchView = searchIcon.actionView as SearchView
        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d(TAG, "QueryTextSubmit: $query")

                    val sharedPref =
                        sharedPreferences.getString(SHARED_PREF_KEY, "This was your 1st Search!!")
                    Toast.makeText(context, sharedPref, Toast.LENGTH_LONG).show()
                    sharedPreferences
                        .edit()
                        .putString(SHARED_PREF_KEY, "Your last search was: $query")
                        .apply()
                    loadMovies(query?.trim())
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

    private fun loadMovies(query: String? = null) {
        mainVM.fetchMovies(query).observe(this, {
            if (query.isNullOrEmpty()) {
                recyclerView.adapter = MovieRecyclerViewAdapter(it.movies)
            } else {
                recyclerView.swapAdapter(MovieRecyclerViewAdapter(it.movies), false)
            }
        })
    }
}