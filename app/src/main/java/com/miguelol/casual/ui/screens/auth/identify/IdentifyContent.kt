package com.miguelol.casual.ui.screens.auth.identify

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miguelol.casual.R
import com.miguelol.casual.ui.components.CustomDialogNotImplemented
import com.miguelol.casual.ui.components.CustomOutlinedButton
import com.miguelol.casual.ui.theme.CasualTheme

@Composable
fun IdentifyContent(
    onSignUp: () -> Unit,
    onLogin: () -> Unit
) {

    val openDialog = remember { mutableStateOf(false) }
    CustomDialogNotImplemented(openDialog = openDialog)

    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.30f),
            color = MaterialTheme.colorScheme.primary
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.height(150.dp ),
                    painter = painterResource(id = R.drawable.casual_logo),
                    contentDescription = ""
                )
            }
        }
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            shadowElevation = 30.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(0.6f))
                Text(text = "Sing Up or Log In", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(10.dp))
                CustomOutlinedButton(text = "Continue with Google", iconId = R.drawable.google_icon) {
                    openDialog.value = true
                }
                Spacer(modifier = Modifier.height(10.dp))
                CustomOutlinedButton(text = "Continue with Facebook", iconId = R.drawable.facebook_icon) {
                    openDialog.value = true
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = "or continue with e-mail",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Divider(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(10.dp))
                CustomOutlinedButton(text = "Register", imageVector = Icons.Outlined.Email) {
                    onSignUp()
                }
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Already have an account?",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray
                    )
                    TextButton(onClick = { onLogin() }) {
                        Text(text = "Log In", style = MaterialTheme.typography.labelMedium)
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}

@Preview
@Composable
fun PreviewSignUpContent() {
    CasualTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            IdentifyContent({}, {})
        }
    }
}