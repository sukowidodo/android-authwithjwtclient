package com.sukowidodo.authwithjwt

import com.sukowidodo.authwithjwt.model.AuthRespModel
import retrofit2.http.POST
import retrofit2.http.Query
import io.reactivex.Observable
import java.util.*

interface RetroInterface{
    @POST("auth/login")
    fun PostLogin(
            @Query("username")username:String,
            @Query("password")password:String
    ):Observable<AuthRespModel>
}
