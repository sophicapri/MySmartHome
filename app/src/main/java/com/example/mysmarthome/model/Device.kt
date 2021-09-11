package com.example.mysmarthome.model

sealed class Device(val productType: ProductType){
    companion object {
        const val LABEL_KEY = "productType"
    }
}