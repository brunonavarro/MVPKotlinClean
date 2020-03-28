package com.brunonavarro.cleanarqdemo.presentation.auth.Registro.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.brunonavarro.cleanarqdemo.R
import com.brunonavarro.cleanarqdemo.base.BaseActivity
import com.brunonavarro.cleanarqdemo.domain.Interactor.auth.registroInteractor.signUpInteractorImpl
import com.brunonavarro.cleanarqdemo.presentation.auth.Registro.RegistroContract
import com.brunonavarro.cleanarqdemo.presentation.auth.Registro.presenter.RegistroPresenter
import com.brunonavarro.cleanarqdemo.presentation.main.view.MainActivity
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : BaseActivity(),RegistroContract.View {

    lateinit var presenter: RegistroContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter=RegistroPresenter(signUpInteractorImpl())
        presenter.attachView(this)
        btn_registro.setOnClickListener {
            signUp()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_registro
    }

    override fun navigateToMain() {
        val intent=Intent(this,MainActivity::class.java)
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun signUp() {
        val user=edt_ruser.text.toString().trim()
        val email=edt_remail.text.toString().trim()
        val pass=edt_rpassword.text.toString().trim()
        val pass1=edt_rcpassword.text.toString().trim()




        if (presenter.checkEmptyName(user)){
            edt_ruser.error="Nombre de usuario vacio"
            return
            //toast(this,"Porfavor complete los campos vacios")
        }
        if (!presenter.checkValidEmail(email)){
            edt_remail.error="Email invalido"
            return
        }

        if (presenter.checkEmptyPasswords(pass,pass1)){
            edt_rpassword.error="Campo vacio"
            edt_rcpassword.error="Campo vacio"
            return
        }

        if (!presenter.checkEmptyPasswordsMatch(pass,pass1)){
            edt_rcpassword.error="El password no coincide"
            return
        }

        presenter.signUp(user,email,pass)
    }

    override fun showProgress() {
        progressBar_signUp.visibility= View.VISIBLE
        btn_registro.visibility=View.GONE
    }

    override fun hideProgress() {
        progressBar_signUp.visibility=View.GONE
        btn_registro.visibility=View.VISIBLE
    }

    override fun showError(errorMessage: String?) {
        toast(this,errorMessage)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detachView()
        presenter.dettachJob()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        presenter.dettachJob()
    }
}
