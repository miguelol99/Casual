package com.miguelol.casual.domain.usecases.auth

import com.miguelol.casual.domain.model.User
import com.miguelol.casual.domain.repositories.UserRepository
import com.miguelol.casual.util.Async
import javax.inject.Inject

data class UserUseCases(
    val createUser: CreateUser
)

class CreateUser @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User): Async<Boolean> = userRepository.createUser(user)
}