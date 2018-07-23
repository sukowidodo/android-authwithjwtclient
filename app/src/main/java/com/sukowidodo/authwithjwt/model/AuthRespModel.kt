package com.sukowidodo.authwithjwt.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthRespModel(
    @SerializedName("auth")@Expose val auth:Boolean? = null,
    @SerializedName("token")@Expose val token:String? = null
)