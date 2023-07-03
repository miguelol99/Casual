package com.miguelol.casual.ui.screens.auth.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.miguelol.casual.ui.components.BackNavigationTopAppBar
import com.miguelol.casual.ui.theme.CasualTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    onNavigateToHome: (String) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            BackNavigationTopAppBar(onBack = onNavigateBack)
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            LogInContent(
                uiState = viewModel.uiState,
                onEmailInput = viewModel::onEmailInput,
                onPasswordInput = viewModel::onPasswordInput,
                onLogInClicked = viewModel::onLogIn
            )
        }
    }
    LogInResponse(
        logInResponse = viewModel.logInResponse,
        onNavigateToHome = onNavigateToHome
    )
}

@Preview
@Composable
fun PreviewLoginScreen() {
    CasualTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LogInContent(LoginState(),{},{},{})
        }
    }
}