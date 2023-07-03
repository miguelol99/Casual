package com.miguelol.casual.ui.screens.home.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.miguelol.casual.ui.components.PlansTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    openDrawer: () -> Unit
) {
    Scaffold(
        topBar = {
            PlansTopAppBar(openDrawer = openDrawer)
        }
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(it), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(text = "ProfileScreen", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
        }
    }

}