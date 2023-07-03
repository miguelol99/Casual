package com.miguelol.casual.data.repositories

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.miguelol.casual.domain.model.User
import com.miguelol.casual.domain.repositories.AuthRepository
import com.miguelol.casual.util.Async
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override val currentUser: FirebaseUser? get() = firebaseAuth.currentUser

    override suspend fun signUp(user: User, password: String): Async<FirebaseUser> {
        return try {
            val result: AuthResult =
                firebaseAuth.createUserWithEmailAndPassword(user.email, password).await()
            Async.Success(checkNotNull(result.user))
        } catch (e: Exception) {
            e.printStackTrace()
            Async.Failure(e)
        }
    }

    override suspend fun logIn(email: String, password: String): Async<FirebaseUser> {
        return try {
            val result: AuthResult =
                firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Async.Success(checkNotNull(result.user))
        } catch (e: Exception) {
            e.printStackTrace()
            Async.Failure(e)
        }
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }


}