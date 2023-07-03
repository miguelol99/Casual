package com.miguelol.casual.domain.usecases.auth

import com.google.firebase.auth.FirebaseUser
import com.miguelol.casual.domain.model.User
import com.miguelol.casual.domain.repositories.AuthRepository
import com.miguelol.casual.util.Async
import javax.inject.Inject

data class AuthUseCases(
    val currentUser: CurrentUser,
    val signUp: SignUp,
    val logIn: LogIn,
    val signOut: SignOut
)

class CurrentUser @Inject constructor(private val authRepository: AuthRepository) {
     operator fun invoke(): FirebaseUser? = authRepository.currentUser
}

class SignUp @Inject constructor(
    private val authRepository: AuthRepository,
    private val userUseCases: UserUseCases
) {

    suspend operator fun invoke(userData: User, password: String): Async<String> {

        val authResponse: Async<FirebaseUser> = authRepository.signUp(userData, password)
        if (authResponse is Async.Failure) return authResponse

        val firebaseUser: FirebaseUser = (authResponse as Async.Success).data
        val user: User  = userData.copy(id = firebaseUser.uid)

        val userResponse: Async<Boolean> = userUseCases.createUser(user)
        if (userResponse is Async.Failure) return userResponse

        return Async.Success(user.id)
    }
}

class LogIn @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): Async<String> {

        val authResponse: Async<FirebaseUser> = authRepository.logIn(email, password)
        if (authResponse is Async.Failure) return authResponse

        val firebaseUser: FirebaseUser = (authResponse as Async.Success).data

        return Async.Success(firebaseUser.uid)
    }
}

class SignOut @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke(): Unit = authRepository.signOut()
}

