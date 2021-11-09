package com.sumaya.hw_week06_day05


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
import com.sumaya.hw_week06_day05.adapter.MovieAdapter
import com.sumaya.hw_week06_day05.ui.MainVM

class MainActivity : AppCompatActivity() {

    private lateinit var moviesRV: RecyclerView

    private val vm by lazy {

        ViewModelProvider(this).get(MainVM::class.java)

    }
    private lateinit var sharedPreferences: SharedPreferences
    private val SHARED_KEY = "last Search"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        sharedPreferences =
            this.getSharedPreferences(" The Movie DB Search Preference", Context.MODE_PRIVATE)

        moviesRV = findViewById(R.id.rv_movies)
        moviesRV.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        loadMovie()

    }


    @SuppressLint("LongLogTag")
    private fun loadMovie(query: String? = null) {
        vm.fetchMoviesList(query).observe(this, {

            if (query.isNullOrEmpty()) {

                moviesRV.adapter = MovieAdapter(it.results)

            }else {

                //update after search
                moviesRV.swapAdapter(MovieAdapter(it.results), false)
            }
            Log.d(" The Movie DB main Response", it.results.toString())
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val tag = "search movie"
        val searchIcon: MenuItem = menu!!.findItem(R.id.app_bar_search)

        val searchMovie = searchIcon.actionView as SearchView
        searchMovie.apply {

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d(tag, "QueryTextSubmit: $query")


                    val sharePref =
                        sharedPreferences.getString(SHARED_KEY, "This is first search !")


                    Toast.makeText(context, "$sharePref", Toast.LENGTH_SHORT).show()

                    sharedPreferences
                        .edit()
                        .putString(SHARED_KEY, "Your last search was : $query")
                        .apply()


                    loadMovie(query?.trim())
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.d(tag, "QueryTextChange: $newText")

                    return false
                }
            })

        }

        return super.onCreateOptionsMenu(menu)
    }




}