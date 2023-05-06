package edu.rit.kc2736.simple5e.Views.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import edu.rit.kc2736.simple5e.Factories.Screen

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<Screen>,
    backgroundColor: Color = Color(0xFF2C2F33),
    selectedColor: Color = Color(0xFF43B04A),
    unselectedColor: Color = Color(0xFF607D8B)
) {
    Surface(
        modifier = Modifier.navigationBarsPadding(),
        color = backgroundColor,
        elevation = 8.dp
    ) {
        BottomNavigation(
            backgroundColor = backgroundColor,
            modifier = Modifier.height(72.dp)
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEachIndexed { index, screen ->
                BottomNavigationItem(
                    icon = {
                        if (index == 2) {
                            Box(modifier = Modifier.background(selectedColor, CircleShape).padding(4.dp)) {
                                Icon(screen.icon, contentDescription = screen.label)
                            }
                        } else {
                            Icon(screen.icon, contentDescription = screen.label)
                        }
                    },
                    label = {
                        Text(
                            screen.label,
                            style = MaterialTheme.typography.caption.copy(
                                color = if (currentRoute == screen.route) selectedColor else unselectedColor
                            )
                        )
                    },
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
}


