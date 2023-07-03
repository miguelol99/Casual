package com.miguelol.casual.ui.screens.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguelol.casual.domain.usecases.auth.AuthUseCases
import com.miguelol.casual.util.Async
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginState(
    var email: String = "",
    var password: String = "",
    var isLoading: Boolean? = null,
    var errorMessage: String? = null
)

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {

    var uiState: LoginState by mutableStateOf(LoginState())
        private set

    var logInResponse: Async<String>? by mutableStateOf(null)
        private set

    fun onLogIn() = viewModelScope.launch {
        logInResponse = Async.Loading
        logInResponse = authUseCases.logIn(email = uiState.email, password = uiState.password)
    }

    fun onEmailInput(s: String) {
        uiState = uiState.copy(email = s)
    }

    fun onPasswordInput(s: String) {
        uiState = uiState.copy(password = s)
    }

}