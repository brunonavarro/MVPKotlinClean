package com.brunonavarro.cleanarqdemo.domain.Interactor.auth.registroInteractor

interface signUpInteractor {
    /*interface signUpCallback{
        fun onSignUpSuccess()
        fun onSignUpFailure(errorMsg:String)

    }*/

    suspend fun signUp(usuario:String,email:String,pass:String)
}