package com.example.mysmarthome.helper


sealed class Resource {
    data class Success(val data: Boolean) : Resource()
    data  class Loading(val data: Boolean? = null) : Resource()
    data class Error(val exception: Throwable) : Resource()
}