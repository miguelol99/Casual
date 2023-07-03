package com.miguelol.casual.ui.screens.auth.identify

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
import com.miguelol.casual.ui.theme.CasualTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdentifyScreen(
    onNavigateToSignUp: () -> Unit,
    onNavigateToLogIn: () -> Unit,
    onNavigateToHome: (String) -> Unit,
    viewModel: IdentifyViewModel = hiltViewModel()
) {

    if (viewModel.currentUser != null) {
        onNavigateToHome(viewModel.currentUser!!.uid)
    }
    else {
        Scaffold() {
            Box(modifier = Modifier.padding(it)) {
                IdentifyContent(
                    onSignUp = onNavigateToSignUp,
                    onLogin = onNavigateToLogIn
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewIdentifyScreen() {
    CasualTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold() {
                Box(modifier = Modifier.padding(it)) {
                    IdentifyContent({}, {})
                }
            }
        }
    }
}