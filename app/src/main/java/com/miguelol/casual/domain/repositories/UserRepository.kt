package com.miguelol.casual.domain.repositories

import com.miguelol.casual.domain.model.User
import com.miguelol.casual.util.Async

interface UserRepository {

    suspend fun createUser(user: User): Async<Boolean>
}