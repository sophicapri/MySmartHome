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
    suspend fun insertLights(lights : List<LightEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeaters(heaters : List<HeaterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRollerShutters(rollerShutters : List<RollerShutterEntity>)

    @Query("SELECT * FROM heaterentity")
    fun getHeaterList() : LiveData<List<HeaterEntity>>

    @Query("SELECT * FROM lightentity")
    fun getLightList() : LiveData<List<LightEntity>>

    @Query("SELECT * FROM rollershutterentity")
    fun getRollerShutterList() : LiveData<List<RollerShutterEntity>>
}