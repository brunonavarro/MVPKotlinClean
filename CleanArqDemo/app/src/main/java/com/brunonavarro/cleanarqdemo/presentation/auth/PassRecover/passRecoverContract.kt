package com.brunonavarro.cleanarqdemo.presentation.auth.PassRecover

interface passRecoverContract {
    interface View {
        fun recoverPassword()
        fun showProgress()
        fun hideProgress()
        fun showError(errorMessage:String?)
        fun navigateToLogin()
    }

    interface Presenter{
        fun attachView(vista: View)
        fun isAttachView():Boolean
        fun detachView()
        fun dettachJob()
        fun sendPasswordRecover(email: String)
        fun checkValidEmail(email:String):Boolean
    }
}