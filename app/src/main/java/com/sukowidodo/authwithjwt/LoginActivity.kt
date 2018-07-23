package com.sukowidodo.authwithjwt

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.sukowidodo.authwithjwt.model.AuthRespModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

abstract class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val service: RetroInterface = RetroClient.getRetrofitInstance().create(RetroInterface::class.java)

        btnLogin.setOnClickListener(View.OnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val postLoginDo = PostLoginDo(service,username, password)
            Log.d("token",postLoginDo.token)
        })
    }

    fun PostLoginDo(service:RetroInterface,username:String,password:String): AuthRespModel {
        return service.PostLogin(username,password)
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
