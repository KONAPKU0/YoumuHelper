package com.rygk.gm.base.nav

import android.app.Activity
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationAnimatedEffect(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String,
    contentAlignment: Alignment = Alignment.Center,
    route: String? = null,
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition)  =
        {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(durationMillis = 300)
            ) + fadeIn(animationSpec = tween(durationMillis = 300))
        },
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition)  =
        {
            slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(durationMillis = 300)
            ) + fadeOut(animationSpec =tween(durationMillis = 300))
        },
    popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
        {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(durationMillis = 300)
            ) + fadeIn(animationSpec = tween(durationMillis = 300))
        },
    popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
        {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(durationMillis = 300)
            ) + fadeOut(animationSpec = tween(durationMillis = 300))
        },
    builder: NavGraphBuilder.() -> Unit,
) {
    val activity = (LocalContext.current as? Activity)
    val navChannel = NavChannel.navChannel
    LaunchedEffect(activity, navController, navChannel) {
        navChannel.collect {
            if (activity?.isFinishing == true) {
                return@collect
            }
            navController.handleComposeNavigationIntent(it)
//            if (LogCat.enabled){
//                navController.currentBackStack.value.forEachIndexed { index, navBackStackEntry ->
//                    LogCat.d("index:$index=NavigationEffects: ${navBackStackEntry.destination.route}",
//                    )
//                }
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
        navController,
        startDestination = startDestination,
        modifier = modifier,
        contentAlignment = contentAlignment,
        route = route,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        builder = builder
    )
}




