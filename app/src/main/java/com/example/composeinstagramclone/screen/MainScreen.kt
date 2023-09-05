package com.example.composeinstagramclone.screen

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeinstagramclone.R

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var isBottomBarVisible = true
    currentDestination?.route?.let { it ->
        isBottomBarVisible = when(it) {
            "add" -> false
            else -> true
        }
    }

    Scaffold(
        bottomBar = {
            if(isBottomBarVisible) BottomNavigation {
                fragments.forEach { screen ->
                    BottomNavigationItem(
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(screen.icon, contentDescription = screen.route, tint = Color.Black, modifier = Modifier.size(30.dp)) },
                        modifier = Modifier
                            .background(Color.White)
                    )
                }
            }
        }
    ) { innerPadding ->
        // TODO startDestination 수정하기
        NavHost(navController = navController, startDestination = Screen.Profile.route, Modifier.padding(innerPadding)) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Search.route) { SearchScreen(navController) }
            composable(Screen.Add.route) { AddScreen() }
            composable(Screen.Reels.route) { ReelsScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }

            composable("searchDetail") { SearchDetailScreen(navController) }
        }
    }
}

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Home : Screen("home", R.string.home, Icons.Filled.Home)
    object Search : Screen("search", R.string.search, Icons.Filled.Search)
    object Add : Screen("add", R.string.add, Icons.Outlined.AddBox)
    object Reels : Screen("reels", R.string.reels, Icons.Filled.Movie)
    object Profile : Screen("profile", R.string.profile, Icons.Filled.Settings)
}

private val fragments = listOf(
    Screen.Home,
    Screen.Search,
    Screen.Add,
    Screen.Reels,
    Screen.Profile,
)