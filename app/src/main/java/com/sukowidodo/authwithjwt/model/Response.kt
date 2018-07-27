package com.sukowidodo.authwithjwt.model

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("auth")
    val auth: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("body")
    val body: Body
)