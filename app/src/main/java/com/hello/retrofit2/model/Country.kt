package com.hello.retrofit2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries_table")
data class Country (
    @PrimaryKey (autoGenerate = true)
    var id: Int,
    var name: String? = null,
    //var topLevelDomain: String? = null,
    var alpha2Code: String? = null,
    var alpha3Code: String? = null,
    //var callingCodes: String? = null,
    var capital: String? = null,
    //var altSpellings: String? = null,
    var region: String
)