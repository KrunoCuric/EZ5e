package edu.rit.kc2736.simple5e.Factories

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import edu.rit.kc2736.simple5e.ViewModels.MainViewModel
import edu.rit.kc2736.simple5e.ViewModels.SpellListViewModel
import edu.rit.kc2736.simple5e.Views.MainView
import edu.rit.kc2736.simple5e.Views.SpellListView

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
//    object Main : Screen("main", "Main Menu", Icons.Default.Home)
//    object Spells : Screen("spells", "Spell list", Icons.Default.Call)
    object Characters : Screen("main", "Chars", Icons.Outlined.AccountCircle)
    object Dice : Screen("spells", "Dice", Icons.Filled.KeyboardArrowUp)
    object NewCharacter : Screen("new_character", "", Icons.Outlined.AddCircle)
    object Class : Screen("class", "Class", Icons.Outlined.Face)
    object Race : Screen("race", "Race", Icons.Outlined.Person)
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
                Screen.Class,
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
    fun create(screen: Screen) {
        when (screen.route) {
            /*TAG => MainView*/
            "main" -> {
                val viewModel = getOrCreateViewModel(MainViewModel::class.java)
                MainView(viewModel)
            }
            /*TAG => SpellListView*/
            "spells" -> {
                val viewModel = getOrCreateViewModel(SpellListViewModel::class.java)
                SpellListView(viewModel)
            }
        }
    }
}
