package com.rygk.gm.base.nav

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

/**
 * @ClassName NavigationEffect.java
 * @author usopp
 * @version 1.0.0
 * @Description
 * @createTime 2022年09月22日 17:48:00
 */

@Composable
fun NavigationEffect(
    navController: NavHostController = rememberNavController(),
    startDestination: String, builder: NavGraphBuilder.() -> Unit,
) {
    val activity = (LocalContext.current as? Activity)
    val flow = NavChannel.navChannel
    LaunchedEffect(activity, navController, flow) {
        flow.collect {
            if (activity?.isFinishing == true) {
                return@collect
            }
            navController.handleComposeNavigationIntent(it)
//            navController.currentBackStack.value.forEachIndexed { index, navBackStackEntry ->
//                LogCat.d(
//                    "NavigationEffects",
//                    "index:$index=NavigationEffects: ${navBackStackEntry.destination.route}",
//                )
//            }
//            navController.backQueue.forEachIndexed { index, navBackStackEntry ->
//                Log.d(
//                    "NavigationEffects",
//                    "index:$index=NavigationEffects: ${navBackStackEntry.destination.route}",
//                )
//            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        builder = builder
    )
}

