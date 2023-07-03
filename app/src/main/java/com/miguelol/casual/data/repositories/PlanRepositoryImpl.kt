package com.miguelol.casual.data.repositories

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import com.miguelol.casual.di.Constants.PLANS
import com.miguelol.casual.domain.model.Plan
import com.miguelol.casual.domain.repositories.PlanRepository
import com.miguelol.casual.util.Async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class PlanRepositoryImpl @Inject constructor(
    @Named(PLANS) private val plansRef: CollectionReference
): PlanRepository {

     /*fun getPlans2(): Flow<Async<List<Plan>>> = callbackFlow {
        val snapshotListener = plansRef.addSnapshotListener { snapshot, e ->
            val response = if (snapshot != null) {
                val plans: List<Plan> = snapshot.toObjects(Plan::class.java)
                Async.Success(plans)
            } else {
                Async.Failure(e)
            }
            trySend(response)
        }
        awaitClose { snapshotListener.remove() }
    }*/

    override fun getPlans() =
        plansRef.snapshots().map { Async.Success(it.toObjects<Plan>()) }

}