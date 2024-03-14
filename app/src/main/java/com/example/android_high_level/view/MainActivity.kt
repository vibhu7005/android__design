package com.example.android_high_level.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_high_level.databinding.ActivityMainBinding
import com.example.android_high_level.databinding.ActivityMainBinding.inflate
import com.example.android_high_level.model.Country
import com.example.android_high_level.viewmodel.CountriesViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private val countryList: MutableList<Country> = mutableListOf()
    private lateinit var countriesViewModel: CountriesViewModel
    private lateinit var countriesAdapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countriesViewModel = ViewModelProvider(this)[CountriesViewModel::class.java]
        viewBinding = inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.rvCountries.layoutManager = LinearLayoutManager(this)
        countriesAdapter = CountryAdapter(countryList)
        viewBinding.rvCountries.adapter = countriesAdapter
    }

    override fun onStart() {
        super.onStart()
        countriesViewModel.fetchCountriesDataFromRepo()
        observeLiveData()
    }

    fun observeLiveData() {
        countriesViewModel.getCountriesLiveData().observe(this) {
            countriesAdapter.modifyList(it as MutableList<Country>)
        }

        countriesViewModel.getDataLoadingCallback().observe(this) { loadingFailed->
            if(loadingFailed == true) {
                viewBinding.listError.visibility = View.VISIBLE
            } else {
                viewBinding.listError.visibility = View.GONE
            }
        }
    }
}