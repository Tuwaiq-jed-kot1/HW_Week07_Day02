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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sumaya.hw_week06_day05.R
import com.sumaya.hw_week06_day05.data.ui.WebViewFragment

class MainActivity : AppCompatActivity() {
    private lateinit var moviesRV: RecyclerView
    private val vm by lazy {
        ViewModelProvider(this).get(MainVM::class.java)
    }
    private lateinit var sharedPreferences: SharedPreferences
    private val SHARED_KEY = "lastSearch"

    fun navigateToWebViewFragment(movieId:Int) {
        val webViewFragmentManager = supportFragmentManager
        val webViewFragmentTransaction = webViewFragmentManager.beginTransaction()
        val webViewFragment = WebViewFragment()

        val mBundle = Bundle()
        mBundle.putString("urlString",movieId.toString())
        webViewFragment.arguments=mBundle
        webViewFragmentTransaction.add(R.id.frameLayout,webViewFragment).commit()
        webViewFragmentTransaction.addToBackStack("webViewFragment")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesRV = findViewById(R.id.recyclerView)
        moviesRV.layoutManager = LinearLayoutManager(this)
        sharedPreferences = this.getSharedPreferences("MovieDBSearchSharePreference", Context.MODE_PRIVATE)
        loadMoviesData()
    }

    private fun loadMoviesData(query: String? = null) {
        vm.fetchInterestingList(query).observe(this, {
            if(query.isNullOrEmpty()){
                moviesRV.adapter = Movie_RV_Adapter(it.results,this)
            } else {
                moviesRV.swapAdapter(Movie_RV_Adapter(it.results,this), false)
            }
            Log.d("Tha Movie Main Response", it.results.toString())
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val TAG = "searchView"
        val searchIcon: MenuItem =  menu!!.findItem(R.id.app_bar_search)
        val searchView = searchIcon.actionView as SearchView
        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d(TAG, "QueryTextSubmit: $query")
                    val sharePref = sharedPreferences.getString(SHARED_KEY, "This was your 1st Search!! Yay!!")
                    Toast.makeText(context, sharePref, Toast.LENGTH_LONG).show()
                    sharedPreferences
                        .edit()
                        .putString(SHARED_KEY, "Your last search was: $query")
                        .apply()
                    loadMoviesData(query?.trim())
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
}