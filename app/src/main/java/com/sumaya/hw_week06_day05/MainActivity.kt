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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var moviesRV: RecyclerView
    private val vm by lazy {
        ViewModelProvider(this).get(MainVM::class.java)
    }
    private lateinit var sharedPreferences: SharedPreferences
    private val SHARED_KEY = "lastSearch"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesRV= findViewById(R.id.rvMovies)
        moviesRV.layoutManager= LinearLayoutManager(this)
        sharedPreferences= this.getSharedPreferences("moviesSearchSharePreferences", Context.MODE_PRIVATE)

        loadMovies()
    }

    private fun loadMovies(query: String? = null) {
        vm.fetchMovies(query).observe(this, {
            if (query.isNullOrEmpty()){
                moviesRV.adapter = MoviesAdapter(it.results)
            } else {
                moviesRV.swapAdapter(MoviesAdapter(it.results), false)
            }

            Log.d("Movies main Response", it.results.toString())
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)
        val TAG= "searchView"
        val searchIcon: MenuItem = menu!!.findItem(R.id.app_bar_search)

        val searchView= searchIcon.actionView as SearchView
        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d(TAG,"QueryTextSubmit: $query")

                    val sharedPrev = sharedPreferences.getString(SHARED_KEY,"This was your 1st Search!! Yay!!")
                    Toast.makeText(context,sharedPrev, Toast.LENGTH_LONG).show()
                    sharedPreferences
                        .edit()
                        .putString(SHARED_KEY, "Your last search was: $query")
                        .apply()
                    loadMovies(query?.trim())
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