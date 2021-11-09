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

    private lateinit var moviesRV : RecyclerView
    private val vm by lazy {
        ViewModelProvider(this).get(MainVM::class.java)
    }
    private lateinit var sharedPreferences: SharedPreferences
    private val SHARED_KEY = "lastSearch"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesRV = findViewById(R.id.rvMovies)
        moviesRV.layoutManager = StaggeredGridLayoutManager(2 ,1 )
        sharedPreferences = this.getSharedPreferences("moviesSearchSharePreferences", Context.MODE_PRIVATE)


        loadMovies()
    }

    private fun loadMovies(query: String? = null) {
        vm.fetchInterestingList(query).observe(this, {
            if(query.isNullOrEmpty()){
                moviesRV.adapter = MoviesAdapter(it.results)
            }else{
                //to start from the begining of the screen
                moviesRV.scrollToPosition(0)
                moviesRV.swapAdapter(MoviesAdapter(it.results), false)
            }
            Log.d("Flicker main Response", it.results.toString())
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val tag = "SearchView"
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchIcon: MenuItem = menu!!.findItem(R.id.app_bar_search)
        val searchView = searchIcon.actionView as SearchView
        searchView.apply {
            setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d(tag,"Query text submit: $query")
                    val sharedPref = sharedPreferences.getString(SHARED_KEY,"This was your 1st search!! YAY!")
                    Toast.makeText(context, sharedPref, Toast.LENGTH_LONG).show()
                    sharedPreferences.edit()
                        .putString(SHARED_KEY, "Your last search was: $query ").apply()
                    loadMovies(query?.trim())
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.d(tag,"Query text change: $newText")
                    return false
                }

            })

        }

        return super.onCreateOptionsMenu(menu)
    }
}
