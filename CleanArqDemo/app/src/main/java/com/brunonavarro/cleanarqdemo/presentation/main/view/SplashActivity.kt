package com.brunonavarro.cleanarqdemo.presentation.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.brunonavarro.cleanarqdemo.R
import com.brunonavarro.cleanarqdemo.base.BaseActivity
import com.brunonavarro.cleanarqdemo.presentation.auth.Login.view.LoginActivity
import com.brunonavarro.cleanarqdemo.presentation.auth.Registro.RegistroContract
import com.brunonavarro.cleanarqdemo.presentation.main.SplashContract

class SplashActivity : BaseActivity() {
    //lateinit var presenter: SplashContract.Presenter

    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //presenter.attachView(this)
        waitTime()
    }

    fun waitTime() {
        //4second splash time
        Handler().postDelayed({
            //start main activity
            goToMain()
        },4000)
    }

    fun goToMain() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        //finish this activity
        finish()
    }
}
