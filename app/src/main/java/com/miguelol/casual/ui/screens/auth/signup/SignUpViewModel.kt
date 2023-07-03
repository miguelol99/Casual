package com.miguelol.casual.ui.screens.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.miguelol.casual.domain.model.User
import com.miguelol.casual.domain.usecases.auth.AuthUseCases
import com.miguelol.casual.domain.usecases.auth.UserUseCases
import com.miguelol.casual.util.Async
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.sign

data class SignUpState(
    var username: String = "",
    var email: String = "",
    var password: String = "",
    var isLoading: Boolean? = null,
    var errorMessage: String? = null
)

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {

    var uiState: SignUpState by mutableStateOf(SignUpState())
        private set

    // if Success, it returns the id of the new user
    var signUpResponse: Async<String>? by mutableStateOf(null)
        private set

    fun onSignUp() {
        val user = User(username = uiState.username, email = uiState.email)
        val password = uiState.password
        viewModelScope.launch {
            signUpResponse = Async.Loading
            signUpResponse = authUseCases.signUp(userData = user, password = password)
        }
    }

    fun onUsernameInput(s: String) {
        uiState = uiState.copy(username = s)
    }

    fun onEmailInput(s: String) {
        uiState = uiState.copy(email = s)
    }

    fun onPasswordInput(s: String) {
        uiState = uiState.copy(password = s)
    }

}