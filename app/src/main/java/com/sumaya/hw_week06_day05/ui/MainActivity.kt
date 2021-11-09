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

    private lateinit var tmdbRV: RecyclerView
    private val vm by lazy { ViewModelProvider(this).get(MainVM::class.java) }
    private lateinit var sharedPreferences: SharedPreferences
    private val SHARED_KEY = "lastSearch"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tmdbRV = findViewById(R.id.rvTmdb)
        tmdbRV.layoutManager = StaggeredGridLayoutManager(2,
            StaggeredGridLayoutManager.VERTICAL)//GridLayoutManager(this, 2)
        sharedPreferences = this.getSharedPreferences("tmdbSearchPref" , Context.MODE_PRIVATE )

        loadTmdbMovies()
    }

    private fun loadTmdbMovies(query: String? = null ) {
        vm.fetchList(query).observe(this, {
            if(query.isNullOrEmpty()){
                tmdbRV.adapter = TmdbAdapter(it.results)

            } else {
                tmdbRV.swapAdapter(TmdbAdapter(it.results), false)
            }
            Log.d("Tmdb Main Response", it.toString())
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchIcon : MenuItem = menu!!.findItem(R.id.app_bar_search)
        val TAG = "searchView"
        val searchView = searchIcon.actionView as SearchView
        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d(TAG, "QueryTextSubmit: $query")
                    val sharedPref = sharedPreferences.getString(
                        SHARED_KEY,
                        "this was your first search"
                    )
                    Toast.makeText(context, sharedPref, Toast.LENGTH_SHORT).show()
                    sharedPreferences
                        .edit()
                        .putString(SHARED_KEY, "Your last search was: $query")
                        .apply()
                    loadTmdbMovies(query?.trim())
                    return true
                }

                override fun onQueryTextChange(newQuery: String?): Boolean {
                    Log.d(TAG, "QueryTextChange: $newQuery")
                    return false
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }
}
