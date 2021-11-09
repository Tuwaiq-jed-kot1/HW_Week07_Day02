package com.sumaya.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sumaya.hw_week06_day05.R
import com.sumaya.hw_week06_day05.ui.MoviesAdapter
import com.sumaya.hw_week06_day05.ui.ViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var moviesRecV: RecyclerView
    private val vm by lazy { ViewModelProvider(this).get(ViewModel::class.java) }
    private lateinit var sharedPre:SharedPreferences
    private val SHARED_KEY="lastSearch"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPre=this.getSharedPreferences("searchedMovie",Context.MODE_PRIVATE)
        moviesRecV=findViewById(R.id.recV)
        moviesRecV.layoutManager=StaggeredGridLayoutManager(
            1,
            StaggeredGridLayoutManager.HORIZONTAL
        )
        loadMoviesPosters()


    }

    private fun loadMoviesPosters(query: String? = null) {
        vm.fetchMovies(query).observe(this, {
            if (query.isNullOrEmpty()) {
                moviesRecV.adapter = MoviesAdapter(it.results)

            } else {
                moviesRecV.swapAdapter(MoviesAdapter(it.results), false)
            }


        })
    }
    override fun onBackPressed() {
        super.onBackPressed()
        loadMoviesPosters()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.search_menu, menu)
        val searchIcon: MenuItem = menu!!.findItem(R.id.app_bar_search)

        val searchView = searchIcon.actionView as SearchView
        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    val sharePref = sharedPre.getString(SHARED_KEY, "This was your last searched movie")
                    Toast.makeText(context, sharePref, Toast.LENGTH_SHORT).show()
                    sharedPre.edit().putString(SHARED_KEY, "Your last searched movie: $query").apply()

                    loadMoviesPosters(query?.trim())
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }

        return super.onCreateOptionsMenu(menu)

    }
}