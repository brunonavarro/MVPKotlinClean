package com.brunonavarro.cleanarqdemo.presentation.auth.PassRecover.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.brunonavarro.cleanarqdemo.R
import com.brunonavarro.cleanarqdemo.base.BaseActivity
import com.brunonavarro.cleanarqdemo.domain.Interactor.auth.recuperarpasswordInteractor.recoveryPassInteractorImpl
import com.brunonavarro.cleanarqdemo.presentation.auth.Login.view.LoginActivity
import com.brunonavarro.cleanarqdemo.presentation.auth.PassRecover.passRecoverContract
import com.brunonavarro.cleanarqdemo.presentation.auth.PassRecover.presenter.passRecoverPresenter
import kotlinx.android.synthetic.main.activity_pass_recover.*

class passRecoverActivity : BaseActivity(),passRecoverContract.View {

    lateinit var presenter:passRecoverPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter=passRecoverPresenter(recoveryPassInteractorImpl())
        presenter.attachView(this)
        btn_recoverPass.setOnClickListener {
            recoverPassword()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_pass_recover
    }

    override fun recoverPassword() {
        val email=edt_email_Recover.text.toString().trim()
        if (!presenter.checkValidEmail(email)) {
            edt_email_Recover.error="Email incorrecto"
            return
        }
        presenter.sendPasswordRecover(email)
    }

    override fun showProgress() {
        btn_recoverPass.visibility= View.GONE
        progressBar_recoverPass.visibility=View.VISIBLE
    }

    override fun hideProgress() {
        btn_recoverPass.visibility= View.VISIBLE
        progressBar_recoverPass.visibility=View.GONE
    }

    override fun showError(errorMessage: String?) {
        toast(this,errorMessage)
    }

    override fun navigateToLogin() {
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        presenter.dettachJob()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detachView()
        presenter.dettachJob()
    }
}
