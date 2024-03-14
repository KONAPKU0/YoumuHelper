package ml.youmu.helper.base.nav

import androidx.navigation.NavController
import ml.youmu.helper.base.nav.NavIntent
import ml.youmu.helper.ext.logD

/**
 * @ClassName NavController.java
 * @author usopp
 * @version 1.0.0
 * @Description
 * @createTime 2022年09月22日 17:45:00
 */


fun NavController.handleComposeNavigationIntent(intent: NavIntent) {
    when (intent) {
        is NavIntent.Back -> {
            if (intent.route != null) {
                "NavIntent.Back==${intent.route}".logD("Navigation")
                popBackStack(intent.route, intent.inclusive)
            } else {
                navigateUp()
            }
        }
        is NavIntent.To -> {
            navigate(intent.route) {
                launchSingleTop = intent.isSingleTop
                intent.popUpToRoute?.let { popUpToRoute ->
                    popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                }
            }
        }
        is NavIntent.Replace -> {
            navigate(intent.route) {
                launchSingleTop = intent.isSingleTop
                currentBackStackEntry?.destination?.route?.let {
                    popBackStack()
                }
            }
        }

        is NavIntent.OffAllTo -> navigate(intent.route) {
            popUpTo(0)
        }
    }
}
