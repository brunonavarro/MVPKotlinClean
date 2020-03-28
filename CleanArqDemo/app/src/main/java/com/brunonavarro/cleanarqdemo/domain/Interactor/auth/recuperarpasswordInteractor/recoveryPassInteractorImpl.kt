package com.brunonavarro.cleanarqdemo.domain.Interactor.auth.recuperarpasswordInteractor

import com.brunonavarro.cleanarqdemo.presentation.auth.PassRecover.exceptions.passRecoverException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class recoveryPassInteractorImpl:recoveryPassInteractor {
    override suspend fun sendPasswordResetEmail(email: String):Unit= suspendCancellableCoroutine {continuation->

        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener{
            if (it.isSuccessful){
                continuation.resume(Unit)
            }else{
                continuation.resumeWithException(passRecoverException(it.exception?.message!!))
            }
        }

    }
}