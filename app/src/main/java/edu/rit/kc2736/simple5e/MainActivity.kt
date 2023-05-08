package edu.rit.kc2736.simple5e

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.rit.kc2736.simple5e.Factories.Screen
import edu.rit.kc2736.simple5e.Factories.ViewFactory
import edu.rit.kc2736.simple5e.Views.Components.BottomNavigationBar
import edu.rit.kc2736.simple5e.databse.createInitialCharModelFile
import edu.rit.kc2736.simple5e.ui.theme.EZ5eDarkTheme
import java.net.URLDecoder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentWrapper()
        }
        createInitialCharModelFile(this)
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
                composable(Screen.SpellDetail.route, listOf(navArgument("spellId") { type = NavType.StringType })) {backStackEntry ->
                    val spellId = backStackEntry.arguments?.getString("spellId")
                    if (spellId != null) {
                        ViewFactory.create(
                            screen = Screen.SpellDetail,
                            navController = navController,
                            index= spellId
                        )
                    }
                }
                composable(Screen.Features.route) { ViewFactory.create(screen = Screen.Features, navController = navController) }
                composable(Screen.FeatureDetail.route, listOf(navArgument("featureId") { type = NavType.StringType })) {backStackEntry ->
                    val featureId = backStackEntry.arguments?.getString("featureId")
                    if (featureId != null) {
                        ViewFactory.create(
                            screen = Screen.FeatureDetail,
                            navController = navController,
                            index = featureId
                        )
                    }
                }
                composable(Screen.CharacterDetail.route, listOf(navArgument("charJson") { type = NavType.StringType })) {backStackEntry ->
                    val charJson = backStackEntry.arguments?.getString("charJson")
                    if (charJson != null) {
                            val decodedFilePath = charJson.let { path -> URLDecoder.decode(path, "UTF-8")}
                            ViewFactory.create(
                            screen = Screen.CharacterDetail,
                            navController = navController,
                            index = decodedFilePath
                        )
                    }
                }
//                composable(Screen.Characters.route) { ViewFactory.create(screen = Screen.Characters, navController = navController) }
//                composable(Screen.NewCharacter.route) { ViewFactory.create(screen = Screen.NewCharacter, navController = navController) }
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

