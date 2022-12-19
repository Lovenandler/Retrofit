package com.hello.retrofit2.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hello.retrofit2.model.Country

@Database(entities = [Country::class], version = 1)
abstract class CountriesDatabase: RoomDatabase() {
    abstract fun getCountryDao(): CountryDao
    companion object{
        @Volatile
        private var INSTANCE: CountriesDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = INSTANCE?: synchronized(LOCK){
            INSTANCE?: buildDatabase(context).also {
                INSTANCE = it
            }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CountriesDatabase::class.java,
            "country-database"
        ).allowMainThreadQueries().build()
    }
}