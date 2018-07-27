package com.sukowidodo.authwithjwt.activity

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.sukowidodo.authwithjwt.R
import com.sukowidodo.authwithjwt.model.Body
import com.sukowidodo.authwithjwt.model.Response
import com.sukowidodo.authwithjwt.retrofit.RetroClient
import com.sukowidodo.authwithjwt.retrofit.RetroInterface
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        val sharedPreferences = getSharedPreferences("mypref",Context.MODE_PRIVATE)
        val service: RetroInterface = RetroClient.getRetrofitInstance().create(RetroInterface::class.java)
        service.getData(sharedPreferences.getString("token",""))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    this::HandleResponse,
                        this::HandleError
                )
    }

    fun HandleResponse(response:Response){
        if(response.auth){
            val body:Body = response.body
            Log.d("Nama Produk",body.name)
        }

    }

    fun HandleError(error:Throwable){
        Log.e("Error",error.message)
    }

}
