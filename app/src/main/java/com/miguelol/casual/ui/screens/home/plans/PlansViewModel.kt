package com.miguelol.casual.ui.screens.home.plans

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguelol.casual.domain.model.Plan
import com.miguelol.casual.domain.usecases.auth.PlanUseCases
import com.miguelol.casual.ui.navigation.DestinationArguments.USER_ID_ARG
import com.miguelol.casual.util.Async
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PlansUiState(
    var items: List<Plan> = emptyList(),
    var filterType: String = "all",
    var isLoading: Boolean = false,
    var message: String? = null
)

@HiltViewModel
class PlansViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val planUseCases: PlanUseCases
) : ViewModel() {

    val userId: String = checkNotNull(savedStateHandle[USER_ID_ARG])

    private val _savedFilterType =
        savedStateHandle.getStateFlow("filter_type", "all")

    private val _plansAsync: Flow<Async<List<Plan>>> = flow {
        val list = listOf(
            Plan(
                name = "Planecito con los panas",
                location = "Plaza San Miguel"
            ),
            Plan(
                name = "Cañas por la tarde",
                location = "Bar Malabar, Valladolid"
            ),
            Plan(
                name = "Nos vamos a tomar por culo a siuuuu",
                location = "Descampado detras de casa"
            )
        )
        emit(Async.Success(list))
    }
    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _message: MutableStateFlow<String?> = MutableStateFlow(null)

    val uiState: StateFlow<PlansUiState> = combine(
        _plansAsync, _savedFilterType, _isLoading, _message
    ) { plansAsync, filterType, isLoading, message ->
        when (plansAsync) {
            Async.Loading -> {
                PlansUiState(isLoading = true)
            }
            is Async.Failure -> {
                PlansUiState(message = plansAsync.exception.message)
            }
            is Async.Success -> {
                PlansUiState(
                    items = plansAsync.data,
                    filterType = filterType,
                    isLoading = isLoading,
                    message = message
                )
            }
            else -> { PlansUiState()}
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = PlansUiState(isLoading = true)
        )

    var plansResponse: Async<List<Plan>>? by mutableStateOf(null)
        private set

    init {
        getPlans()
    }

    private fun getPlans() = viewModelScope.launch {
        plansResponse = Async.Loading
        planUseCases.getPlans().collect {
            plansResponse = it
        }
    }


}