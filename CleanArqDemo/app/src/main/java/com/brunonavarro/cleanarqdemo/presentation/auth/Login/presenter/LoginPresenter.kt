package com.brunonavarro.cleanarqdemo.presentation.auth.Login.presenter

import com.brunonavarro.cleanarqdemo.domain.Interactor.auth.loginInteractor.signInteractor
import com.brunonavarro.cleanarqdemo.presentation.auth.Login.LoginContract
import kotlinx.coroutines.*
import javax.security.auth.login.LoginException
import kotlin.coroutines.CoroutineContext

class LoginPresenter(signInteractor: signInteractor): LoginContract.Presenter,CoroutineScope {

    var view:LoginContract.View?=null
    var signInteractor:signInteractor?=null

    private val job= Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main+job

    init {
        this.signInteractor=signInteractor
    }

    override fun attachView(view: LoginContract.View) {
        this.view=view
    }

    override fun dettachView() {
        view=null
    }

    override fun dettachJob() {
        coroutineContext.cancel()
    }

    override fun isViewAttached(): Boolean {
        return view!=null
    }

    override fun signInUserWithEmailAndPassword(email: String, pass: String) {

        launch {
            try {
                view?.showProgressBar()
                signInteractor?.signInWithEmailAndPassword(email,pass)
                if (isViewAttached()){
                    view?.hideProgressBar()
                    view?.navigateToMain()
                }
            }catch (e:LoginException){
                if (isViewAttached()) {
                    view?.hideProgressBar()
                    view?.showError(e.message)
                }
            }

        }

        /*view?.showProgressBar()
        signInteractor?.signInWithEmailAndPassword(email,pass,object : signInteractor.signCallback{
            override fun onSignInSuccess() {
                if (isViewAttached()){
                    view?.hideProgressBar()
                    view?.navigateToMain()
                }
            }

            override fun onSignInFailure(errorMsg: String) {
                if (isViewAttached()){
                    view?.hideProgressBar()
                    view?.showError(errorMsg)

                }

            }

        })*/
    }

    override fun checkEmptyFields(email: String, pass: String):Boolean {
        return email.isEmpty()||pass.isEmpty()
    }

}