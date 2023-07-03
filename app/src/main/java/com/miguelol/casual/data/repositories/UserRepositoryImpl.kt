package com.miguelol.casual.data.repositories

import com.google.firebase.firestore.CollectionReference
import com.miguelol.casual.di.Constants.USERS
import com.miguelol.casual.domain.model.User
import com.miguelol.casual.domain.repositories.UserRepository
import com.miguelol.casual.util.Async
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class UserRepositoryImpl @Inject constructor(
    @Named(USERS) private val usersRef: CollectionReference,
) : UserRepository {

    override suspend fun createUser(user: User): Async<Boolean> {
        return try {
            usersRef.document(user.id).set(user).await()
            Async.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Async.Failure(e)
        }
    }
}