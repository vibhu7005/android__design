package com.example.android_high_level.network

import com.example.android_high_level.model.Country
import retrofit2.http.GET

interface CountriesApi {
    @GET("DevTides/countries/master/countriesV2.json")
    suspend fun getCountries(): List<Country>
}