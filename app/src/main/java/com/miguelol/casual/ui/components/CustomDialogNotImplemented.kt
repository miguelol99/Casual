package com.miguelol.casual.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miguelol.casual.R
import com.miguelol.casual.ui.theme.CasualTheme

@Composable
fun CustomDialogNotImplemented(openDialog: MutableState<Boolean>) {

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            icon = {
                Image(
                    modifier = Modifier.height(40.dp),
                    painter = painterResource(id = R.drawable.computer_icon),
                    contentDescription = ""
                )
            },
            title = { Text(text = "Not implemented") },
            text = {
                Text(
                    text = "This functionality is yet to be implemented as it is beyond the scope " +
                            "of this proyect."
                )
            },
            dismissButton = {
                Button(onClick = { openDialog.value = false }) {
                    Text("Close")
                }
            },
            confirmButton = {}
        )
    }
}

@Preview
@Composable
fun PreviewSignUpContent() {
    CasualTheme {
        CustomDialogNotImplemented(openDialog = remember { mutableStateOf(true) })
    }
}