package com.brunonavarro.cleanarqdemo.presentation.auth.Registro.presenter

import androidx.core.util.PatternsCompat
import com.brunonavarro.cleanarqdemo.domain.Interactor.auth.registroInteractor.signUpInteractor
import com.brunonavarro.cleanarqdemo.presentation.auth.Registro.RegistroContract
import kotlinx.coroutines.*
import javax.security.auth.login.LoginException
import kotlin.coroutines.CoroutineContext

class RegistroPresenter(signUpInteractor: signUpInteractor):RegistroContract.Presenter, CoroutineScope {



    var view: RegistroContract.View?=null
    var signUpInteractor: signUpInteractor?=null

    private val job=Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main+job

    init {
        this.signUpInteractor=signUpInteractor
    }

    override fun attachView(view: RegistroContract.View) {
        this.view=view
    }

    override fun isAttachView(): Boolean {
        return view!=null
    }

    override fun detachView() {
        view=null
    }

    override fun dettachJob() {
        coroutineContext.cancel()
    }

    override fun checkEmptyName(name: String): Boolean {
        return name.isEmpty()
    }

    override fun checkValidEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun checkEmptyPasswords(pw1: String, pw2: String): Boolean {
        return pw1.isEmpty()||pw2.isEmpty()
    }

    override fun checkEmptyPasswordsMatch(pw1: String, pw2: String): Boolean {
        return pw1==pw2
    }

    override fun signUp(fullname: String, email: String, pass: String) {
        launch {
            try {
                view?.showProgress()
                signUpInteractor?.signUp(fullname,email,pass)
                if (isAttachView()){
                    view?.hideProgress()
                    view?.navigateToMain()
                }
            }catch (e: LoginException){
                if (isAttachView()) {
                    view?.hideProgress()
                    view?.showError(e.message)
                }
            }
        }
        /*view?.showProgress()
        signUpInteractor?.signUp(fullname,email,pass,object:signUpInteractor.signUpCallback{
            override fun onSignUpSuccess() {
                view?.navigateToMain()
                view?.hideProgress()
            }

            override fun onSignUpFailure(errorMsg: String) {
                view?.showError(errorMsg)
                view?.hideProgress()
            }

        })*/
    }


}