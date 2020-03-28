package com.brunonavarro.cleanarqdemo.presentation.auth.Registro



interface RegistroContract {
    interface View{
        fun navigateToMain()
        fun signUp()
        fun showProgress()
        fun hideProgress()
        fun showError(errorMessage:String?)
    }

    interface Presenter{
        fun attachView(view:View)
        fun isAttachView():Boolean
        fun detachView()
        fun dettachJob()
        fun checkEmptyName(name:String):Boolean
        fun checkValidEmail(email:String):Boolean
        fun checkEmptyPasswords(pw1:String,pw2:String):Boolean
        fun checkEmptyPasswordsMatch(pw1:String,pw2:String):Boolean
        fun signUp(fullname: String,email: String,pass: String)
    }


}