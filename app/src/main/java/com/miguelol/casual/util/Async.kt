package com.miguelol.casual.util

import java.lang.Exception

sealed class Async<out T> {
    object Loading: Async<Nothing>()
    data class Failure(val exception: Exception): Async<Nothing>()
    data class Success<out T>(val data: T): Async<T>()
}