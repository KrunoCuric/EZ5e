package edu.rit.kc2736.simple5e.Views.Components
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import edu.rit.kc2736.simple5e.Factories.Screen

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<Screen>,
    selectedColor: Color = Color(0xFF43B04A),
    unselectedColor: Color = Color(0xFF607D8B)
) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                selectedContentColor = selectedColor,
                unselectedContentColor = unselectedColor,
                alwaysShowLabel = false
            )
        }
    }
}


