package com.example.mysmarthome.model

sealed class Device(open val id: Int, var productType: ProductType){
    companion object {
        const val LABEL_KEY = "productType"
    }
}