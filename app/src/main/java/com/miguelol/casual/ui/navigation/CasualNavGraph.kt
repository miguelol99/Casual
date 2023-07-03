package com.miguelol.casual.ui.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.miguelol.casual.ui.components.CustomModalDrawer
import com.miguelol.casual.ui.navigation.DestinationArguments.PLAN_ID_ARG
import com.miguelol.casual.ui.navigation.DestinationArguments.USER_ID_ARG
import com.miguelol.casual.ui.navigation.Screens.EDIT_PROFILE_SCREEN
import com.miguelol.casual.ui.navigation.Screens.IDENTIFY_SCREEN
import com.miguelol.casual.ui.navigation.Screens.LOGIN_SCREEN
import com.miguelol.casual.ui.navigation.Screens.PLANS_SCREEN
import com.miguelol.casual.ui.navigation.Screens.PLAN_ADD_EDIT_SCREEN
import com.miguelol.casual.ui.navigation.Screens.PLAN_DETAIL_SCREEN
import com.miguelol.casual.ui.navigation.Screens.PROFILE_DETAIL_SCREEN
import com.miguelol.casual.ui.navigation.Screens.SIGNUP_SCREEN
import com.miguelol.casual.ui.screens.home.plans.PlansScreen
import com.miguelol.casual.ui.screens.home.profile.ProfileScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object Screens {
    const val IDENTIFY_SCREEN = "identify"
    const val SIGNUP_SCREEN = "signup"
    const val LOGIN_SCREEN = "login"
    const val PLANS_SCREEN = "plans"
    const val PLAN_DETAIL_SCREEN = "plan"
    const val PLAN_ADD_EDIT_SCREEN = "plan/addEdit"
    const val PROFILE_DETAIL_SCREEN = "profile"
    const val EDIT_PROFILE_SCREEN = "profile/edit"
}

object DestinationArguments {
    const val USER_ID_ARG = "userId"
    const val PLAN_ID_ARG = "planId"
}

object Destinations {
    const val AUTH_GRAPH_ROUTE = "auth"
    const val IDENTIFY_ROUTE = IDENTIFY_SCREEN
    const val SIGNUP_ROUTE = SIGNUP_SCREEN
    const val LOGIN_ROUTE = LOGIN_SCREEN
    const val PLANS_ROUTE = "$PLANS_SCREEN/{$USER_ID_ARG}"
    const val PLAN_DETAIL = "$PLAN_DETAIL_SCREEN/{$PLAN_ID_ARG}"
    const val PLAN_ADD_EDIT_ROUTE = "$PLAN_ADD_EDIT_SCREEN/{$USER_ID_ARG}"
    const val PROFILE_DETAIL_ROUTE = "$PROFILE_DETAIL_SCREEN/{$USER_ID_ARG}"
    const val EDIT_PROFILE_ROUTE = "$EDIT_PROFILE_SCREEN/{$USER_ID_ARG}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CasualNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    startDestination: String = Destinations.AUTH_GRAPH_ROUTE
) {

    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute: String = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {

        authNavGraph(navController = navController)

        composable(
            route = Destinations.PLANS_ROUTE,
            arguments = listOf(navArgument(USER_ID_ARG) { type = NavType.StringType})
        )
        {
            CustomModalDrawer(drawerState, currentRoute, navController) {
                PlansScreen(openDrawer = { coroutineScope.launch { drawerState.open() } })
            }
        }

        composable(route = Destinations.PROFILE_DETAIL_ROUTE) {
            CustomModalDrawer(drawerState, currentRoute, navController) {
                ProfileScreen(openDrawer = { coroutineScope.launch { drawerState.open() } })
            }
        }
    }

}