package com.brunonavarro.cleanarqdemo.domain.Interactor.auth.loginInteractor

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.security.auth.login.LoginException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class signInInteractorImpl: signInteractor {


    //, listener: signInteractor.signCallback
    override suspend fun signInWithEmailAndPassword(email: String, pass: String): Unit = suspendCancellableCoroutine {continuation->
        // Choose authentication providers
        /*val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.FacebookBuilder().build(),
            AuthUI.IdpConfig.TwitterBuilder().build())*/

        /*FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass)
            .addOnCompleteListener {
                /*if(it.isSuccessful){
                    listener.onSignInSuccess()
                }else{
                    listener.onSignInFailure(it.exception?.message!!)
                }*/

            }*/
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass).addOnCompleteListener() {
            if (it.isSuccessful) {
                continuation.resume(Unit)
            }else{
                continuation.resumeWithException(LoginException(it.exception?.message))
            }
        }
    }


}