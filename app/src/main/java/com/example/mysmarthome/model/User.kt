package com.example.mysmarthome.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)var id: Int = 0,
    var firstName: String,
    var lastName: String,
    var address: Address,
    var birthDate: Long
) {

    data class Address(
        var city: String, var postalCode: Int, var street: String,
        var streetCode: String, var country: String
    )
}