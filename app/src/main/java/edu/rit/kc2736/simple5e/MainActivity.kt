package edu.rit.kc2736.simple5e

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.rit.kc2736.simple5e.Factories.Screen
import edu.rit.kc2736.simple5e.Factories.ViewFactory
import edu.rit.kc2736.simple5e.Views.Components.BottomNavigationBar
import edu.rit.kc2736.simple5e.ui.theme.EZ5eDarkTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentWrapper()
        }
    }
}

@Composable
fun ContentWrapper() {
    EZ5eDarkTheme(darkTheme = true) {
        val scaffoldState = rememberScaffoldState()
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { BottomNavigationBar(navController, Screen.items()) },
            scaffoldState = scaffoldState,
        ) { padding ->
            NavHost(
                navController,
                startDestination = Screen.Characters.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(Screen.Characters.route) { ViewFactory.create(screen = Screen.Characters) }
                composable(Screen.Dice.route) { ViewFactory.create(screen = Screen.Dice) }
                composable(Screen.NewCharacter.route) { /* ... */ }
                composable(Screen.Class.route) { /* ... */ }
                composable(Screen.Race.route) { /* ... */ }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EZ5eDarkTheme(true) {
        ContentWrapper()
    }
}