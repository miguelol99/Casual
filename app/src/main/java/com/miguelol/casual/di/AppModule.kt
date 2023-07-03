package com.miguelol.casual.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.miguelol.casual.data.repositories.AuthRepositoryImpl
import com.miguelol.casual.data.repositories.PlanRepositoryImpl
import com.miguelol.casual.data.repositories.UserRepositoryImpl
import com.miguelol.casual.di.Constants.PLANS
import com.miguelol.casual.di.Constants.USERS
import com.miguelol.casual.domain.repositories.AuthRepository
import com.miguelol.casual.domain.repositories.PlanRepository
import com.miguelol.casual.domain.repositories.UserRepository
import com.miguelol.casual.domain.usecases.auth.AuthUseCases
import com.miguelol.casual.domain.usecases.auth.CreateUser
import com.miguelol.casual.domain.usecases.auth.CurrentUser
import com.miguelol.casual.domain.usecases.auth.GetPlans
import com.miguelol.casual.domain.usecases.auth.LogIn
import com.miguelol.casual.domain.usecases.auth.PlanUseCases
import com.miguelol.casual.domain.usecases.auth.SignOut
import com.miguelol.casual.domain.usecases.auth.SignUp
import com.miguelol.casual.domain.usecases.auth.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

object Constants {
    const val USERS = "users"
    const val PLANS = "plans"
}


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    //DATA SOURCES
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()
    @Provides
    @Named(USERS)
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)
    @Provides
    @Named(PLANS)
    fun providePlansRef(db: FirebaseFirestore): CollectionReference = db.collection(PLANS)

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUserRepository(impl: UserRepositoryImpl): UserRepository = impl

    @Provides
    fun providePlanRepository(impl: PlanRepositoryImpl): PlanRepository = impl

    @Provides
    fun provideAuthUseCases(
        repository: AuthRepository,
        userUseCases: UserUseCases
    ) = AuthUseCases(
        currentUser = CurrentUser(repository),
        signUp = SignUp(repository, userUseCases),
        logIn = LogIn(repository),
        signOut = SignOut(repository)
    )

    @Provides
    fun provideUserUseCases(repository: UserRepository) = UserUseCases(
        createUser = CreateUser(repository)
    )

    @Provides
    fun providePlanUseCases(repository: PlanRepository) =PlanUseCases(
        getPlans = GetPlans(repository)
    )

}