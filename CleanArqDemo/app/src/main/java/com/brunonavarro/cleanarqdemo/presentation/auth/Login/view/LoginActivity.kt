package com.brunonavarro.cleanarqdemo.presentation.auth.Login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.brunonavarro.cleanarqdemo.R
import com.brunonavarro.cleanarqdemo.base.BaseActivity
import com.brunonavarro.cleanarqdemo.domain.Interactor.auth.loginInteractor.signInInteractorImpl
import com.brunonavarro.cleanarqdemo.presentation.auth.Login.LoginContract
import com.brunonavarro.cleanarqdemo.presentation.auth.Login.presenter.LoginPresenter
import com.brunonavarro.cleanarqdemo.presentation.auth.PassRecover.view.passRecoverActivity
import com.brunonavarro.cleanarqdemo.presentation.auth.Registro.view.RegistroActivity
import com.brunonavarro.cleanarqdemo.presentation.main.view.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(),LoginContract.View {

    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter=LoginPresenter(signInInteractorImpl())
        presenter.attachView(this)
        btn_login.setOnClickListener {
            signIn()
        }
        txt_registro.setOnClickListener {
            navigateToRegister()
        }
        txt_pass_recover.setOnClickListener {
            navigateToPassReset()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun showError(mess: String?) {
        toast(this,mess)
    }

    override fun showProgressBar() {
        progressBar_signIn.visibility = View.VISIBLE
        btn_login.visibility=View.GONE
    }

    override fun hideProgressBar() {
        progressBar_signIn.visibility=View.GONE
        btn_login.visibility=View.VISIBLE
    }

    override fun signIn() {
        val email=edt_email.text.toString().trim()
        val password=edt_password.text.toString().trim()
        if(presenter.checkEmptyFields(email,password)){
            toast(this,"Porfavor ingrese Email y Contraseña")
        }else{
            presenter.signInUserWithEmailAndPassword(email,password)
        }
    }

    override fun navigateToMain() {
        val intent=Intent(this,MainActivity::class.java)
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun navigateToRegister() {
        val intent=Intent(this,RegistroActivity::class.java)
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun navigateToPassReset() {
        startActivity(Intent(this,passRecoverActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettachView()
        presenter.dettachJob()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.dettachView()
        presenter.dettachJob()
    }
}
