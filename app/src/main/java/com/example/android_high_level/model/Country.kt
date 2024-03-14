package com.example.android_high_level.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    var countryName: String? = null,
    var capital: String? = null,
    @SerializedName("flagPNG")
    var flag: String? = null
)