package com.miguelol.casual.ui.components

import androidx.lifecycle.ViewModel
import com.miguelol.casual.domain.usecases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignOutViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
): ViewModel() {
    fun signOut() = authUseCases.signOut()
}