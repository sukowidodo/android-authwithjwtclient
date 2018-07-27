package com.sukowidodo.authwithjwt.retrofit

import com.sukowidodo.authwithjwt.model.LoginModel
import com.sukowidodo.authwithjwt.model.Response
import io.reactivex.Observable
import retrofit2.http.*

interface RetroInterface{
    @POST("auth/login")
    fun PostLogin(
            @Query("username")username:String,
            @Query("password")password:String
    ):Observable<LoginModel>

    @GET("users")
    fun getData(
            @Header("x-access-token") token:String
    ):Observable<Response>
}
