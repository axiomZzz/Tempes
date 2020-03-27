package com.github.axiomzzz.tempes.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.github.axiomzzz.tempes.data.db.entity.Data



@Database(
    entities = [Data::class],
    version = 1
)
//@TypeConverters(LocalDateConverter::class)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurentWeatherDao
//    abstract fun futureWeatherDao(): FutureWeatherDao
//    abstract fun weatherLocationDao(): WeatherLocationDao

    companion object {
        @Volatile private var instance: ForecastDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                    ForecastDatabase::class.java, "futureWeatherEntries.db")
                    .build()
    }
}