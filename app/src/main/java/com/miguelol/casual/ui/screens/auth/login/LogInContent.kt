package com.miguelol.casual.ui.screens.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miguelol.casual.ui.components.CustomDialogNotImplemented
import com.miguelol.casual.ui.components.CustomPasswordField
import com.miguelol.casual.ui.components.CustomTextField
import com.miguelol.casual.ui.screens.auth.identify.IdentifyContent
import com.miguelol.casual.ui.screens.signup.SignUpState
import com.miguelol.casual.ui.theme.CasualTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInContent(
    uiState: LoginState,
    onEmailInput: (String) -> Unit,
    onPasswordInput: (String) -> Unit,
    onLogInClicked: () -> Unit
) {

    val openDialog = remember { mutableStateOf(false) }
    CustomDialogNotImplemented(openDialog = openDialog)

    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.90f),
            shadowElevation = 30.dp
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "Log in", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(30.dp))
                CustomTextField(
                    value = uiState.email,
                    icon = Icons.Outlined.Email,
                    label = "Email",
                    onValueChange =  { onEmailInput(it) }
                )
                Spacer(modifier = Modifier.height(15.dp))
                CustomPasswordField(
                    value = uiState.password,
                    onValueChange =  { onPasswordInput(it) }
                )
                Spacer(modifier = Modifier.height(25.dp))
                TextButton(onClick = { openDialog.value = true}) {
                    Text(
                        text = "Have you forgotten your password?",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelMedium)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onLogInClicked() }) {
                    Text(text = "Log In")
                }
            }
        }
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary
        ) {

        }
    }
}

@Preview
@Composable
fun PreviewLoginContent() {
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