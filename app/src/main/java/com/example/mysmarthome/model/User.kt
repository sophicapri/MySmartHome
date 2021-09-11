package com.example.mysmarthome.model

data class User(
    var firstName: String,
    var lastName: String,
    var address: Address,
    var birthDate: Long
)

data class Address(
    var city: String, var postalCode: Int, var street: String,
    var streetCode: String, var country: String
)