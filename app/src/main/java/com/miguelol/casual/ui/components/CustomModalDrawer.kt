package com.miguelol.casual.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.miguelol.casual.R
import com.miguelol.casual.ui.navigation.Destinations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomModalDrawer(
    drawerState: DrawerState,
    currentRoute: String,
    navController: NavHostController,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    content: @Composable () -> Unit
) {

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.fillMaxWidth(0.85f),
                drawerShape = RectangleShape
            ) {
                CustomDrawerContent(
                    currentRoute = currentRoute,
                    navigateToPlans = {
                        navController.popBackStack(route = Destinations.PLANS_ROUTE, true)
                        navController.navigate(route = Destinations.PLANS_ROUTE)
                    },
                    navigateToProfile = {
                        navController.popBackStack(route = Destinations.PLANS_ROUTE, true)
                        navController.navigate(route = Destinations.PROFILE_DETAIL_ROUTE)
                    },
                    navigateToAuth =  {
                        navController.popBackStack(route = Destinations.AUTH_GRAPH_ROUTE, true)
                        navController.navigate(route = Destinations.AUTH_GRAPH_ROUTE)
                    },
                    closeDrawer = { coroutineScope.launch { drawerState.close() } }
                )
            }
        }
    ) {
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDrawerContent(
    currentRoute: String,
    navigateToPlans: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToAuth: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignOutViewModel = hiltViewModel() //OJO CON ESTO TODO
) {

    Column(modifier = modifier.fillMaxSize()) {
        DrawerHeader()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            NavigationDrawerItem(
                label = { Text(text = "Plans") },
                icon = {
                    Image(
                        modifier = Modifier.size(25.dp),
                        painter = painterResource(id = R.drawable.beer_icon),
                        contentDescription = ""
                    )
                },
                selected = currentRoute == Destinations.PLANS_ROUTE,
                onClick = {
                    navigateToPlans();
                    closeDrawer()
                }
            )
            NavigationDrawerItem(
                label = { Text(text = "Profile") },
                icon = {
                    Icon(
                        modifier = Modifier.size(25.dp),
                        imageVector = Icons.Outlined.Person,
                        contentDescription = ""
                    )
                },
                selected = currentRoute == Destinations.PROFILE_DETAIL_ROUTE,
                onClick = { navigateToProfile(); closeDrawer() }
            )
            NavigationDrawerItem(
                label = { Text(text = "Notifications") },
                icon = {
                    Icon(
                        modifier = Modifier.size(25.dp),
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = ""
                    )
                },
                selected = false,
                onClick = { /*TODO*/ closeDrawer() }
            )
            Spacer(modifier = Modifier.weight(1f))
            NavigationDrawerItem(
                label = { Text(text = "Sign Out") },
                selected = false,
                onClick = {
                    closeDrawer()
                    viewModel.signOut()
                    navigateToAuth()
                }
            )
        }

        /*        DrawerButton(
                    painter = painterResource(id = R.drawable.ic_list),
                    label = stringResource(id = R.string.list_title),
                    isSelected = currentRoute == TodoDestinations.TASKS_ROUTE,
                    action = {
                        navigateToTasks()
                        closeDrawer()
                    }
                )
                DrawerButton(
                    painter = painterResource(id = R.drawable.ic_statistics),
                    label = stringResource(id = R.string.statistics_title),
                    isSelected = currentRoute == TodoDestinations.STATISTICS_ROUTE,
                    action = {
                        navigateToStatistics()
                        closeDrawer()
                    }
                )
         */
    }
}

@Composable
private fun DrawerHeader(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .height(192.dp)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.casual_logo),
            contentDescription = "",
            modifier = Modifier.width(300.dp)
        )
    }
}