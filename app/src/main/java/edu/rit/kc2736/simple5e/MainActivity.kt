package edu.rit.kc2736.simple5e

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.rit.kc2736.simple5e.Factories.Screen
import edu.rit.kc2736.simple5e.Factories.ViewFactory
import edu.rit.kc2736.simple5e.Views.Components.BottomNavigationBar
import edu.rit.kc2736.simple5e.Views.SpellDetailView
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
        BackgroundImageBox(navController = navController, scaffoldState = scaffoldState)
    }
}
@Composable
fun BackgroundImageBox(navController: NavHostController, scaffoldState: ScaffoldState) {
    Box{
        Scaffold(
            bottomBar = { BottomNavigationBar(navController, Screen.items()) },
            scaffoldState = scaffoldState,
//            backgroundColor = Color.Transparent
        ) { padding ->
            NavHost(
                navController,
                startDestination = Screen.Characters.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(Screen.Characters.route) { ViewFactory.create(screen = Screen.Characters,  navController = navController) }
                composable(Screen.Dice.route) { ViewFactory.create(screen = Screen.Dice, navController = navController) }
                composable(Screen.NewCharacter.route) { ViewFactory.create(screen = Screen.NewCharacter, navController = navController) }
                composable(Screen.Spells.route) { ViewFactory.create(screen = Screen.Spells, navController = navController) }
                composable(Screen.SpellDetail.route) { ViewFactory.create(screen = Screen.SpellDetail, navController = navController) }
                composable(Screen.Race.route) {  }
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