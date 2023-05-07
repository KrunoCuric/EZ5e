package edu.rit.kc2736.simple5e.Factories

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import edu.rit.kc2736.simple5e.ViewModels.MainViewModel
import edu.rit.kc2736.simple5e.ViewModels.SpellListViewModel
import edu.rit.kc2736.simple5e.Views.MainView
import edu.rit.kc2736.simple5e.Views.SpellDetailView
import edu.rit.kc2736.simple5e.Views.SpellListView

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Characters : Screen("characters", "Chars", Icons.Outlined.AccountCircle)
    object Dice : Screen("dice", "Dice", Icons.Filled.Casino)
    object NewCharacter : Screen("new_character", "", Icons.Outlined.AddCircle)
    object Spells : Screen("spells", "Spells", Icons.Outlined.Book)
    object Race : Screen("race", "Race", Icons.Outlined.Person)
    object SpellDetail: Screen("spellDetail", "Spell details", Icons.Outlined.Face)
    //Gets a array of all objects declared above this
    companion object {
        fun values(): Array<Screen> {
            val objects = Screen::class.sealedSubclasses.flatMap { it.objectInstance?.let(::listOf) ?: emptyList() }
            return objects.toTypedArray()
        }
        fun items(): List<Screen> {
            return listOf(
                Screen.Characters,
                Screen.Dice,
                Screen.NewCharacter,
                Screen.Spells,
                Screen.Race
            )
        }
    }
}

object ViewFactory {
    private val viewModelMap = mutableMapOf<Class<*>, ViewModel>()

    fun <T : ViewModel> getOrCreateViewModel(viewModelClass: Class<T>): T {
        var viewModel = viewModelMap[viewModelClass]
        if (viewModel == null) {
            viewModel = viewModelClass.newInstance()
            viewModelMap[viewModelClass] = viewModel
        }
        return viewModel as T
    }

    @Composable
    fun create(screen: Screen, navController: androidx.navigation.NavController) {
        when (screen.route) {
            /*TAG => MainView*/
            "characters" -> {
                val viewModel = getOrCreateViewModel(MainViewModel::class.java)
                MainView(viewModel)
            }
            /*TAG => SpellListView*/
            "spells" -> {
                val viewModel = getOrCreateViewModel(SpellListViewModel::class.java)
                SpellListView(viewModel, navController)
            }
            "spellDetail" -> {
                val viewModel = getOrCreateViewModel(SpellListViewModel::class.java)
                SpellDetailView(viewModel, navController)
            }
        }
    }
}
