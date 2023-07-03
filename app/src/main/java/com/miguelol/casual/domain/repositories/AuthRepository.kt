package com.miguelol.casual.domain.repositories

import com.google.firebase.auth.FirebaseUser
import com.miguelol.casual.domain.model.User
import com.miguelol.casual.domain.usecases.auth.CurrentUser
import com.miguelol.casual.util.Async

interface AuthRepository {

    val currentUser: FirebaseUser?
    suspend fun signUp(user: User, password: String): Async<FirebaseUser>
    suspend fun logIn(email: String, password: String) : Async<FirebaseUser>
    fun signOut()
}