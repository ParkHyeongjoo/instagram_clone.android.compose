package com.example.composeinstagramclone.ui.main

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeinstagramclone.ui.adds.AddScreen
import com.example.composeinstagramclone.ui.homes.HomeScreen
import com.example.composeinstagramclone.ui.profiles.ProfileScreen
import com.example.composeinstagramclone.ui.reelses.ReelsScreen
import com.example.composeinstagramclone.ui.searches.SearchDetailScreen
import com.example.composeinstagramclone.ui.searches.SearchScreen
import com.example.composeinstagramclone.utils.navigation.BottomNavi

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
                        icon = { if (screen.icon != null) Icon(screen.icon, contentDescription = screen.resourceId, tint = Color.Black, modifier = Modifier.size(30.dp)) },
                        modifier = Modifier
                            .background(Color.White)
                    )
                }
            }
        }
    ) { innerPadding ->
        // TODO startDestination 수정하기
        NavHost(
            navController = navController,
            startDestination = BottomNavi.ProfileScreen.route,
            Modifier.padding(innerPadding),
            enterTransition = { fadeIn(animationSpec = tween(0)) },
            exitTransition = { fadeOut(animationSpec = tween(0)) }
        ) {
            composable(BottomNavi.HomeScreen.route) { HomeScreen() }
            composable(BottomNavi.SearchScreen.route) { SearchScreen(navController) }
            composable(BottomNavi.AddScreen.route) { AddScreen() }
            composable(BottomNavi.ReelsScreen.route) { ReelsScreen() }
            composable(BottomNavi.ProfileScreen.route) { ProfileScreen() }

            composable(BottomNavi.SearchDetail.route) { SearchDetailScreen(navController) }
        }
    }
}

private val fragments = listOf(
    BottomNavi.HomeScreen,
    BottomNavi.SearchScreen,
    BottomNavi.AddScreen,
    BottomNavi.ReelsScreen,
    BottomNavi.ProfileScreen,
)