package com.example.mysmarthome.data.local.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.mysmarthome.model.Device

@Dao
interface DeviceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLightList(lights: List<DeviceEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeaterList(heaters: List<DeviceEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRollerShutterList(rollerShutters: List<DeviceEntity>)

    @Update
    suspend fun updateDevice(device: DeviceEntity)

    @Query("SELECT * FROM deviceentity")
    fun getDeviceList() : LiveData<List<DeviceEntity>>

    @RawQuery(observedEntities = [DeviceEntity::class])
    fun getFilteredDeviceList(query: SupportSQLiteQuery) : LiveData<List<DeviceEntity>>

    @Delete
    suspend fun deleteDeviceList(devices: List<DeviceEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDevice(device: DeviceEntity): Long
}