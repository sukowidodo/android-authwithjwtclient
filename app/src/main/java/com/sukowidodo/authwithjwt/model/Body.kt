package com.sukowidodo.authwithjwt.model

import com.google.gson.annotations.SerializedName

data class Body(
        @SerializedName("productid") val productid: Int,
        @SerializedName("name") val name: String,
        @SerializedName("price") val price: Int
)