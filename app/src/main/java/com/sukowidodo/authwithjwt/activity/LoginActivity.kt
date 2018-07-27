package com.sukowidodo.authwithjwt.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.sukowidodo.authwithjwt.R
import com.sukowidodo.authwithjwt.retrofit.RetroClient
import com.sukowidodo.authwithjwt.retrofit.RetroInterface
import com.sukowidodo.authwithjwt.model.LoginModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class LoginActivity : AppCompatActivity() {

    var pref:SharedPreferences?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val service: RetroInterface = RetroClient.getRetrofitInstance().create(RetroInterface::class.java)

        btnLogin.setOnClickListener(View.OnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            PostLoginDo(service,username, password)
        })
    }

    fun PostLoginDo(service: RetroInterface, username:String, password:String) {
        service.PostLogin(username,password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::HandleResponse,
                        this::HandleError
                )

    }

    fun HandleResponse(authrespmodel:LoginModel){
        val token:String = authrespmodel.token!!;
        Log.d("token",token)
        pref = getSharedPreferences("mypref",Context.MODE_PRIVATE)
        val editor = pref!!.edit()
        editor.putString("token",token)
        editor.commit()

        val gotohome = Intent(this,HomeActivity::class.java)
        startActivity(gotohome)
    }

    fun HandleError(error:Throwable){
        Log.e("Error",error.message)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
