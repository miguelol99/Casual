package com.miguelol.casual.ui.screens.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miguelol.casual.ui.components.CustomPasswordField
import com.miguelol.casual.ui.components.CustomTextField
import com.miguelol.casual.ui.theme.CasualTheme

@Composable
fun SignUpContent(
    uiState: SignUpState,
    onUsernameInput: (String) -> Unit,
    onEmailInput: (String) -> Unit,
    onPasswordInput: (String) -> Unit,
    onSignUpClicked: () -> Unit
) {

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
                Text(text = "Sign in", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(30.dp))
                CustomTextField(
                    value = uiState.username,
                    icon = Icons.Outlined.Person,
                    label = "Username",
                    onValueChange =  { onUsernameInput(it) }
                )
                Spacer(modifier = Modifier.height(15.dp))
                CustomTextField(
                    value = uiState.email,
                    icon = Icons.Outlined.Email,
                    label = "Email",
                    onValueChange =  { onEmailInput(it) }
                )
                Spacer(modifier = Modifier.height(15.dp))
                CustomPasswordField(value = uiState.password, onValueChange =  { onPasswordInput(it)} )
                Spacer(modifier = Modifier.height(50.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onSignUpClicked() }) {
                    Text(text = "Register")
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
            SignUpContent(SignUpState(),{}, {}, {}, {})
        }
    }
}