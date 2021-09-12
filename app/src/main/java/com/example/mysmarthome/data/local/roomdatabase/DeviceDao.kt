package com.example.mysmarthome.data.local.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mysmarthome.model.Device
import com.example.mysmarthome.model.Heater
import com.example.mysmarthome.model.Light
import com.example.mysmarthome.model.RollerShutter
import io.reactivex.rxjava3.core.Flowable

@Dao
interface DeviceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLights(lights: kotlin.collections.List<com.example.mysmarthome.data.local.roomdatabase.DeviceEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeaters(heaters: kotlin.collections.List<com.example.mysmarthome.data.local.roomdatabase.DeviceEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRollerShutters(rollerShutters: kotlin.collections.List<com.example.mysmarthome.data.local.roomdatabase.DeviceEntity>)

    @Query("SELECT * FROM deviceentity")
    fun getDeviceList() : LiveData<List<DeviceEntity>>

    @Query(""" SELECT * FROM deviceentity WHERE productType LIKE '%' || 'Light' || '%' """)
    fun getFilteredList() : LiveData<List<DeviceEntity>>

}