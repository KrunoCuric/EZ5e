package edu.rit.kc2736.simple5e.Factories

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.FactCheck
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.rit.kc2736.simple5e.ViewModels.CharacterViewModel
import edu.rit.kc2736.simple5e.ViewModels.FeatureListViewModel
import edu.rit.kc2736.simple5e.ViewModels.MainViewModel
import edu.rit.kc2736.simple5e.ViewModels.SpellListViewModel
import edu.rit.kc2736.simple5e.Views.CharacterListView
import edu.rit.kc2736.simple5e.Views.CharacterView
import edu.rit.kc2736.simple5e.Views.FeatureDetailView
import edu.rit.kc2736.simple5e.Views.FeatureListView
import edu.rit.kc2736.simple5e.Views.MainView
import edu.rit.kc2736.simple5e.Views.NewCharacterView
import edu.rit.kc2736.simple5e.Views.SpellDetailView
import edu.rit.kc2736.simple5e.Views.SpellListView

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Characters : Screen("characters", "Chars", Icons.Outlined.AccountCircle)
    object Dice : Screen("dice", "Dice", Icons.Filled.Casino)
    object NewCharacter : Screen("new_character", "", Icons.Outlined.AddCircle)
    object Spells : Screen("spells", "Spells", Icons.Outlined.Book)
    object Features : Screen("features", "Features", Icons.Outlined.FactCheck)
    object SpellDetail: Screen("spellDetail/{spellId}", "Spell details", Icons.Outlined.Book)
    object FeatureDetail: Screen("featureDetail/{featureId}", "Feature details", Icons.Outlined.FactCheck)
    object CharacterDetail: Screen("characterDetail/{charJson}", "Character details", Icons.Outlined.Face)
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
                Screen.Features
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
    fun create(screen: Screen, navController: androidx.navigation.NavController, index:String = "") {
        when (screen.route) {
            /*TAG => CharacterListView*/
            "characters" -> {
                CharacterListView(navController)
            }
            /*TAG => CharacterView*/
            "characterDetail/{charJson}" -> {
                CharacterView(navController, index)
            }
            /*TAG => SpellListView*/
            "spells" -> {
                val viewModel = getOrCreateViewModel(SpellListViewModel::class.java)
                SpellListView(viewModel, navController)
            }
            /*TAG => FeatureListView*/
            "features" -> {
                val viewModel = getOrCreateViewModel(FeatureListViewModel::class.java)
                FeatureListView(viewModel, navController)
            }
            /*TAG => NewCharacterView*/
            "new_character" -> {
                NewCharacterView(navController)
            }
            /*TAG => SpellDetailView*/
            "spellDetail/{spellId}" -> {
                val viewModel = getOrCreateViewModel(SpellListViewModel::class.java)
                SpellDetailView(viewModel, navController, index)
            }
            /*TAG => FeatureDetailView*/
            "featureDetail/{featureId}" -> {
                val viewModel = getOrCreateViewModel(FeatureListViewModel::class.java)
                FeatureDetailView(viewModel, navController, index)
            }
        }
    }
}
class CharacterViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
