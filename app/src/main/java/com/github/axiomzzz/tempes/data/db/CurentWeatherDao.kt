package com.github.axiomzzz.tempes.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.axiomzzz.tempes.data.db.entity.CURRENT_WEATHER_ID
import com.github.axiomzzz.tempes.data.db.entity.Data
import com.github.axiomzzz.tempes.data.db.unitlocalaized.MetricCurrentWeatherEntry

@Dao
interface CurentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun upsert(data: List<Data>)

    @Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
     fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry>
}