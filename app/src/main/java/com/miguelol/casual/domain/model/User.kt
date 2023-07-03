package com.miguelol.casual.domain.model

data class User(
    var id: String = "",
    var username: String = "",
    var email: String = "",
    var pictureUrl: String? = null,
)
