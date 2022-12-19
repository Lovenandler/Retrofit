package com.hello.retrofit2.room

import androidx.room.*
import com.hello.retrofit2.model.Country

@Dao
interface CountryDao {
    @Insert
    fun addCountry(country: Country)

    @Query("SELECT * FROM countries_table ORDER BY name ASC")
    fun getAllData(): List<Country>

    @Update
    suspend fun updateCountry(country: Country)

    @Query("DELETE FROM countries_table")
    fun deleteCountry(): Int
}