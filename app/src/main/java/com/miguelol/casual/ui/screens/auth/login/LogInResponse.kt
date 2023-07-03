package com.miguelol.casual.ui.screens.auth.login

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.miguelol.casual.ui.components.CustomProgressIndicator
import com.miguelol.casual.util.Async

@Composable
fun LogInResponse(
    logInResponse: Async<String>?,
    onNavigateToHome: (String) -> Unit
) {

    when (logInResponse) {
        Async.Loading -> {
            CustomProgressIndicator()
        }

        is Async.Success -> {
            LaunchedEffect(Unit) {
                onNavigateToHome(logInResponse.data)
            }
        }

        is Async.Failure -> {
            val errMsg = logInResponse.exception?.message ?: "Unknown Error"
            Toast.makeText(LocalContext.current, errMsg, Toast.LENGTH_LONG).show()
        }

        else -> {}
    }
}