package com.miguelol.casual.ui.screens.home.plans

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.miguelol.casual.ui.components.PlansTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlansScreen(
    openDrawer: () -> Unit,
    viewModel: PlansViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            PlansTopAppBar(openDrawer = openDrawer)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Add, "")
            }
        }
    ) { paddingValues ->

        val uiState: PlansUiState by viewModel.uiState.collectAsState()

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            PlansContent(
                modifier = Modifier.padding(paddingValues),
                plans = uiState.items
            )
        }

    }
}

