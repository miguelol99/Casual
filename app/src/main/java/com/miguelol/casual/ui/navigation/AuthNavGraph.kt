package com.miguelol.casual.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.miguelol.casual.ui.screens.auth.identify.IdentifyScreen
import com.miguelol.casual.ui.screens.auth.login.LogInScreen
import com.miguelol.casual.ui.screens.signup.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {

    navigation(
        route = Destinations.AUTH_GRAPH_ROUTE,
        startDestination = Destinations.IDENTIFY_ROUTE
    ) {

        composable(route = Destinations.IDENTIFY_ROUTE) {
            IdentifyScreen(
                onNavigateToSignUp = { navController.navigate(route = Destinations.SIGNUP_ROUTE) },
                onNavigateToLogIn = {  navController.navigate(route = Destinations.LOGIN_ROUTE) },
                onNavigateToHome = { userId ->
                    navController.popBackStack(Destinations.AUTH_GRAPH_ROUTE, true)
                    navController.navigate("${Screens.PLANS_SCREEN}/$userId")
                }
            )
        }

        composable(route = Destinations.SIGNUP_ROUTE) {
            SignUpScreen(
                onNavigateToHome = {userId ->
                    navController.popBackStack(Destinations.AUTH_GRAPH_ROUTE, true)
                    navController.navigate("${Screens.PLANS_SCREEN}/$userId")
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(route = Destinations.LOGIN_ROUTE) {
            LogInScreen(
                onNavigateToHome = {userId ->
                    navController.popBackStack(Destinations.AUTH_GRAPH_ROUTE, true)
                    navController.navigate("${Screens.PLANS_SCREEN}/$userId")
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }

}