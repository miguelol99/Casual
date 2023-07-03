package com.miguelol.casual.domain.model

import com.google.firebase.firestore.ServerTimestamp

data class Plan(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val location: String = "",
    val timestamp: ServerTimestamp? = null,
    val imageUrl: String = "",
    val host: User = User(),
    val guests: List<User> = listOf()
)
