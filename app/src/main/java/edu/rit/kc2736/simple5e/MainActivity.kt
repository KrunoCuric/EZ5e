package edu.rit.kc2736.simple5e

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.rit.kc2736.simple5e.Factories.Screen
import edu.rit.kc2736.simple5e.Factories.ViewFactory
import edu.rit.kc2736.simple5e.ViewModels.MainViewModel
import edu.rit.kc2736.simple5e.Views.Components.BottomNavigationBar
import edu.rit.kc2736.simple5e.Views.MainView
import edu.rit.kc2736.simple5e.ui.theme.EZ5eTheme
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
           // EZ5eTheme {
                // A surface container using the color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
                    Scaffold(
                        bottomBar = { BottomNavigationBar(navController, Screen.items()) }
                    ) {padding ->
                        NavHost(navController, startDestination = Screen.Characters.route, modifier = Modifier.padding(padding)) {
                            composable(Screen.Characters.route) { /* ... */ }
                            composable(Screen.Dice.route) { /* ... */ }
                            composable(Screen.NewCharacter.route) { /* ... */ }
                            composable(Screen.Class.route) { /* ... */ }
                            composable(Screen.Race.route) { /* ... */ }
                        }
                    }
//                }
            //}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EZ5eTheme {
    }
}