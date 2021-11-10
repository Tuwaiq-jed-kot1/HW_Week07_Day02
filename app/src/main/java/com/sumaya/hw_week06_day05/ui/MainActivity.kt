package com.sumaya.hw_week06_day05.ui


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
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sumaya.hw_week06_day05.R

class MainActivity : AppCompatActivity() {
    private lateinit var tmdbRV: RecyclerView

    private lateinit var sharedpreference: SharedPreferences
    private val SHARED_KEY = "LastSearch"

    private val vm by lazy {
        ViewModelProvider(this).get(MainVM::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vm = ViewModelProvider(this).get(MainVM::class.java)
        sharedpreference =
            this.getSharedPreferences("TMDBSearchSharedPreference", Context.MODE_PRIVATE)

        tmdbRV = findViewById(R.id.rvTMDB)

        // layout of images inside recycler view
        tmdbRV.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        loadMovies()
    }

    private fun loadMovies(query: String? = null) {
        vm.fetchInterestingList(query).observe(this, {

            if (query.isNullOrEmpty()) {
                tmdbRV.adapter = TMDBAdapter(it.results)
            } else {
                tmdbRV.swapAdapter(TMDBAdapter(it.results), false)
            }
            Log.d("TMDB Main Response", it.results.toString())
        })
    }

    // here we will inflate the menu search item and it's icon then connect it with action
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val TAG = "searchView"
        val searchIcon: MenuItem = menu!!.findItem(R.id.app_bar_search)
        val searchView = searchIcon.actionView as SearchView

        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                @SuppressLint("CommitPrefEdits")
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d(TAG, "Query TextSubmit $query")

                    // Using Shared Preference
                    val sharedPref =
                        sharedpreference.getString(SHARED_KEY, "This was your 1st Search")
                    Toast.makeText(context, sharedPref, Toast.LENGTH_LONG).show()

                    // If we want to add to the SharedPreference we need to open the file for edit first at the
                    // end is apply , between them is to add data
                    sharedpreference.edit()
                        .putString(SHARED_KEY, "Your last search was: $query")
                        .apply()
                    loadMovies(query?.trim())
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.d(TAG, "Query TextChanged $newText")
                    return false
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }
}