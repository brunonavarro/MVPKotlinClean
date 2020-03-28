package com.brunonavarro.cleanarqdemo.domain.Interactor.auth.loginInteractor

interface signInteractor {
    /*interface signCallback{
        fun onSignInSuccess()
        fun onSignInFailure(errorMsg:String)

    }*/

    suspend fun signInWithEmailAndPassword(email:String,pass:String)//,listener:signCallback

    //fun onActivityResult(requestCode:Int,resultCode:Int, data:Intent)
}