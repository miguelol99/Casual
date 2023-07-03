package com.miguelol.casual.domain.usecases.auth

import com.miguelol.casual.domain.model.Plan
import com.miguelol.casual.domain.repositories.PlanRepository
import com.miguelol.casual.util.Async
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class PlanUseCases(
    val getPlans: GetPlans
)

class GetPlans @Inject constructor(private val repository: PlanRepository) {
    operator fun invoke(): Flow<Async<List<Plan>>> = repository.getPlans()
}
