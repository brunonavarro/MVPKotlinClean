package com.brunonavarro.cleanarqdemo.domain.Interactor.auth.registroInteractor

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.security.auth.login.LoginException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class signUpInteractorImpl: signUpInteractor {
    override suspend fun signUp(usuario: String, email: String, pass: String): Unit = suspendCancellableCoroutine {continuation->
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    val profileUpdate=UserProfileChangeRequest.Builder()
                        .setDisplayName(usuario)
                        .build()
                    FirebaseAuth.getInstance().currentUser?.updateProfile(profileUpdate)?.addOnCompleteListener { itprofile ->
                        if (itprofile.isSuccessful){
                            //listener.onSignUpSuccess()
                            continuation.resume(Unit)
                        }
                    }
                }else{
                    //listener.onSignUpFailure(it.exception?.message!!)
                    continuation.resumeWithException(LoginException(it.exception?.message))
                }
            }
    }
}