package com.example.composeinstagramclone.utils.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.composeinstagramclone.R

sealed class BottomNavi(val route: String, val resourceId: String, val icon: ImageVector?) {
    object HomeScreen : BottomNavi("home", R.string.home.toString(), Icons.Outlined.Home)
    object SearchScreen : BottomNavi("search", R.string.search.toString(), Icons.Filled.Search)
    object AddScreen : BottomNavi("add", R.string.add.toString(), Icons.Outlined.AddBox)
    object ReelsScreen : BottomNavi("reels", R.string.reels.toString(), Icons.Outlined.Movie)
    object ProfileScreen : BottomNavi("profile", R.string.profile.toString(), Icons.Filled.Settings)

    object SearchDetail : BottomNavi("searchDetail", R.string.searchDetail.toString(), null)
}

sealed class ProfileTabItem(val route: String, val resourceId: String, val icon: ImageVector) {
    object AllTabScreen : ProfileTabItem("allTab", R.string.allTab.toString(), Icons.Outlined.GridOn)
    object ReelsTabScreen : ProfileTabItem("reelsTab", R.string.reelsTab.toString(), Icons.Outlined.Movie)
    object TagTabScreen : ProfileTabItem("tagTab", R.string.tagTab.toString(), Icons.Outlined.AccountBox)
}


//sealed class Destination(protected val route: String, vararg params: String) {
//    val fullRoute: String = if (params.isEmpty()) route else {
//        val builder = StringBuilder(route)
//        params.forEach { builder.append("/{${it}}") }
//        builder.toString()
//    }
//
//    sealed class NoArgumentsDestination(route: String, icon: ImageVector): Destination(route) {
//        operator fun invoke(): String = route
//    }
//    object HomeScreen : NoArgumentsDestination("home", Icons.Filled.Home)
//    object SearchScreen : NoArgumentsDestination("search", Icons.Filled.Search)
//    object AddScreen : NoArgumentsDestination("add", Icons.Outlined.AddBox)
//    object ReelsScreen : NoArgumentsDestination("reels", Icons.Filled.Movie)
//    object ProfileScreen : NoArgumentsDestination("profile", Icons.Filled.Settings)
//
//    object UserScreen : Destination("user", "firstName", "lastName") {
//        const val FIRST_NAME_KEY = "firstName"
//        const val LAST_NAME_KEY = "lastName"
//
//        operator fun invoke(firstName: String, lastName: String): String = route.appendParams(
//            FIRST_NAME_KEY to firstName,
//            LAST_NAME_KEY to lastName
//        )
//    }
//}
//
//internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
//    val builder = StringBuilder(this)
//
//    params.forEach {
//        it.second?.toString()?.let { arg ->
//            builder.append("/$arg")
//        }
//    }
//    return builder.toString()
//}