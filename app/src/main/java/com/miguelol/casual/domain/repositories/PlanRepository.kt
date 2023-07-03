package com.miguelol.casual.domain.repositories

import com.miguelol.casual.domain.model.Plan
import com.miguelol.casual.util.Async
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PlanRepository {

    fun getPlans(): Flow<Async<List<Plan>>>
}