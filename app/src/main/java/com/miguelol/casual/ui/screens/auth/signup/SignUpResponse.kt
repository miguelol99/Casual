package com.miguelol.casual.ui.screens.signup

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseUser
import com.miguelol.casual.ui.components.CustomProgressIndicator
import com.miguelol.casual.util.Async

@Composable
fun SignUpResponse(
    signUpResponse: Async<String>?,
    onNavigateToHome: (String) -> Unit
) {

    when (signUpResponse) {
        Async.Loading -> {
            CustomProgressIndicator()
        }

        is Async.Success -> {
            LaunchedEffect(Unit) {
                onNavigateToHome(signUpResponse.data)
            }
        }

        is Async.Failure -> {
            val errMsg = signUpResponse.exception?.message ?: "Unknown Error"
            Toast.makeText(LocalContext.current, errMsg, Toast.LENGTH_LONG).show()
        }

        else -> {}
    }
}