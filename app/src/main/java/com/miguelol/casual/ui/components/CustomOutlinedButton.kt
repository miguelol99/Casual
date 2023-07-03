package com.miguelol.casual.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miguelol.casual.R
import com.miguelol.casual.ui.theme.CasualTheme

@Composable
fun CustomOutlinedButton(
    text: String,
    iconId: Int? = null,
    imageVector: ImageVector? = null,
    onClick: () -> Unit
) {

    OutlinedButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onClick() }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (iconId != null)
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(iconId),
                    contentDescription = ""
                )
            else if (imageVector != null)
                Image(
                    modifier = Modifier.size(20.dp),
                    imageVector = imageVector,
                    contentDescription = ""
                )
            Text(
                modifier = Modifier.weight(1f),
                text = text,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun PreviewCustomOutlinedButton() {
    CasualTheme {
        CustomOutlinedButton(text = "Button", iconId = R.drawable.google_icon) {}
    }
}
