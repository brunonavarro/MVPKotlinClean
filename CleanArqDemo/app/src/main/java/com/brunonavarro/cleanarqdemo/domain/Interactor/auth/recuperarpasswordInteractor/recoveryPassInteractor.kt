package com.brunonavarro.cleanarqdemo.domain.Interactor.auth.recuperarpasswordInteractor

interface recoveryPassInteractor {

    suspend fun sendPasswordResetEmail(email:String)
}