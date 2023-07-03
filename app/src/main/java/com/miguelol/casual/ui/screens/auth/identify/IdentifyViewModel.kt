package com.miguelol.casual.ui.screens.auth.identify

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.miguelol.casual.domain.usecases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IdentifyViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
): ViewModel() {

    val currentUser: FirebaseUser? by mutableStateOf(authUseCases.currentUser())
}