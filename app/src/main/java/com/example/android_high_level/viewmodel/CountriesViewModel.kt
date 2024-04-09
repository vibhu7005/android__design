package com.example.android_high_level.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_high_level.model.Country
import com.example.android_high_level.model.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountriesViewModel : ViewModel() {
    private val countriesLiveData = MutableLiveData<List<Country>>()
    private val dataLoadingFailure = MutableLiveData(false)

    fun fetchCountriesDataFromRepo() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                countriesLiveData.postValue(CountryRepository.getCountries())
                dataLoadingFailure.postValue(false)
            } catch (exception: Exception) {
                Log.d("Api exception", exception.message.toString())
                dataLoadingFailure.postValue(true)
            }
        }
    }

    fun getDataLoadingCallback() = dataLoadingFailure as LiveData<Boolean>

    fun getCountriesLiveData() = countriesLiveData as LiveData<List<Country>>
}