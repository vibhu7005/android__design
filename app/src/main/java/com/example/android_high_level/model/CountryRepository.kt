package com.example.android_high_level.model

import com.example.android_high_level.network.CountriesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object CountryRepository {

    private val apiService =
        Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com") // Replace with your API base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesApi::class.java)

    // Create API service interface
    suspend fun getCountries(): List<Country> = apiService.getCountries()
}