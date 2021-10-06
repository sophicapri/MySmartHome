package com.example.mysmarthome.model

import androidx.sqlite.db.SimpleSQLiteQuery
import com.squareup.moshi.Json

enum class ProductType(val value: String) {
    @Json(name = "RollerShutter")
    ROLLER_SHUTTER("RollerShutter"),
    @Json(name = "Light")
    LIGHT("Light"),
    @Json(name = "Heater")
    HEATER("Heater");


    companion object {
        private const val BASE_QUERY = "SELECT * FROM deviceentity WHERE "
        fun queryBuilder(productTypes: List<ProductType>): SimpleSQLiteQuery {
            var stringQuery = BASE_QUERY
            var emptyQuery = true

            productTypes.forEach {
                if (emptyQuery) {
                    stringQuery += "productType LIKE '%${it.name}%'"
                    emptyQuery = false
                } else
                    stringQuery += "OR productType LIKE '%${it.name}%'"
            }
            return SimpleSQLiteQuery(stringQuery)
        }
    }
}