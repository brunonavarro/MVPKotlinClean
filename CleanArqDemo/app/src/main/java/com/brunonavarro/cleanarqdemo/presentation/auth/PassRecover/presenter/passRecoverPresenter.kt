package com.brunonavarro.cleanarqdemo.presentation.auth.PassRecover.presenter

import androidx.core.util.PatternsCompat
import com.brunonavarro.cleanarqdemo.domain.Interactor.auth.recuperarpasswordInteractor.recoveryPassInteractor
import com.brunonavarro.cleanarqdemo.presentation.auth.PassRecover.exceptions.passRecoverException
import com.brunonavarro.cleanarqdemo.presentation.auth.PassRecover.passRecoverContract
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class passRecoverPresenter(recoveryPassInteractor: recoveryPassInteractor):passRecoverContract.Presenter, CoroutineScope {

    var view: passRecoverContract.View?=null
    var passrecoverInteractor: recoveryPassInteractor?=null

    private val job= Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main+job

    init {
        this.passrecoverInteractor=recoveryPassInteractor
    }

    override fun attachView(vista: passRecoverContract.View) {
        this.view=vista
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

    override fun sendPasswordRecover(email: String) {

        launch {
            try {
                view?.showProgress()
                passrecoverInteractor?.sendPasswordResetEmail(email)
                view?.hideProgress()
                view?.navigateToLogin()
            }catch (e:passRecoverException){
                view?.hideProgress()
                view?.showError(e.message)
            }
        }
    }

    override fun checkValidEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }
}