package com.brunonavarro.cleanarqdemo.presentation.auth.Login

interface LoginContract {
    interface View{
        fun showError(mess:String?)
        fun showProgressBar()
        fun hideProgressBar()
        fun signIn()
        fun navigateToMain()
        fun navigateToRegister()
        fun navigateToPassReset()
    }

    interface Presenter{
        fun attachView(view: View)
        fun dettachView()
        fun dettachJob()
        fun isViewAttached():Boolean
        fun signInUserWithEmailAndPassword(email:String,pass:String)
        fun checkEmptyFields(email: String,pass: String):Boolean
    }
}