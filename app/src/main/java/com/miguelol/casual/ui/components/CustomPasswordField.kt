package com.miguelol.casual.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miguelol.casual.R
import com.miguelol.casual.ui.theme.CasualTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordField(
    value: String,
    onValueChange: (String) -> Unit
) {

    var visibility by remember { mutableStateOf(false) }
    var iconId by remember { mutableStateOf(R.drawable.visibility_false) }

    iconId = if (visibility) R.drawable.visibility_true else R.drawable.visibility_false

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = { onValueChange(it) },
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.Lock, contentDescription = "")
                      },
        trailingIcon = {

               Icon(
                   modifier = Modifier
                       .clickable { visibility = ! visibility }
                       .height(20.dp),
                   painter = painterResource(id = iconId),
                   contentDescription = "")

        },
        label = { Text(text = "Password") },
        shape = RoundedCornerShape(40.dp),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (visibility) VisualTransformation.None
        else PasswordVisualTransformation()
    )

}

@Preview
@Composable
fun PreviewCustomPasswordField() {
    CasualTheme {
        CustomPasswordField(
            value = "",
            onValueChange = {}
        )
    }
}