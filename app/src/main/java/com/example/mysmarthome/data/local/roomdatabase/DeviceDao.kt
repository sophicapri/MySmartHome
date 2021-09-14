package com.example.mysmarthome.data.local.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
interface DeviceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLights(lights: List<DeviceEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeaters(heaters: List<DeviceEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRollerShutters(rollerShutters: List<DeviceEntity>)

    @Update
    suspend fun updateDevice(device: DeviceEntity)

    @Query("SELECT * FROM deviceentity")
    fun getDeviceList() : LiveData<List<DeviceEntity>>

    @RawQuery(observedEntities = [DeviceEntity::class])
    fun getFilteredList(query: SupportSQLiteQuery) : LiveData<List<DeviceEntity>>

    @Delete
    suspend fun deleteDevices(devices: List<DeviceEntity>)

    /* @Query("SELECT * FROM deviceentity WHERE id = :id")
  fun getDeviceById(id: Int): LiveData<DeviceEntity>
*/

}