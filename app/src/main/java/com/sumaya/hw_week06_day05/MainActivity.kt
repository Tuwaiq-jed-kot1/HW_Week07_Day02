package com.sumaya.hw_week06_day05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sumaya.hw_week06_day05.ui.DbAdapter
import com.sumaya.hw_week06_day05.ui.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerMovies: RecyclerView

    private val vm by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerMovies = findViewById(R.id.DBmovies)
        recyclerMovies.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        loadMoviesList()
    }

    private fun loadMoviesList(query: String? = null) {

        vm.fetchMoviesList(query).observe(this, {
            if (query.isNullOrEmpty()) {
                recyclerMovies.adapter = DbAdapter(it.results)
            } else {
                recyclerMovies.swapAdapter(DbAdapter(it.results), false)
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val tag = "search view"
        val searchIcon: MenuItem = menu!!.findItem(R.id.app_bar_search)
        val searchView = searchIcon.actionView as SearchView


        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d(tag, "Query Text submit $query")
                    loadMoviesList(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.d(tag, "Query Text Cheange $newText")
                    return false
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }
}

