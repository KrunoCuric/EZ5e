package edu.rit.kc2736.simple5e.Views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import edu.rit.kc2736.simple5e.Factories.CharacterViewModelFactory
import edu.rit.kc2736.simple5e.ViewModels.CharacterViewModel
import edu.rit.kc2736.simple5e.databse.Character

@Composable
fun CharacterView(navController: NavController, index: String) {
    val localContext = LocalContext.current
    val viewModel: CharacterViewModel = viewModel(factory = CharacterViewModelFactory(localContext))
    val characterState = viewModel.loadedCharacter.value
    LaunchedEffect(index) {
        viewModel.loadCharacterWithPath(index)
    }

    characterState?.let {character->
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = character.name,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(text = "Age: ${character.age}")
            Text(text = "Race: ${character.race.name}")
            Text(text = "Class: ${character.characterClass.name}")
            Text(text = "Level: ${character.level}")
            Text(text = "Alignment: ${character.alignment.name}")

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Backstory", fontWeight = FontWeight.Bold)
                    Text(text = character.backstory)
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Values", fontWeight = FontWeight.Bold)
                    Text(text = character.values)
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Bonds", fontWeight = FontWeight.Bold)
                    Text(text = character.bonds)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Stats", fontWeight = FontWeight.Bold)
            Text(text = "STR: ${character.stats.str}")
            Text(text = "INT: ${character.stats.int}")
            Text(text = "DEX: ${character.stats.dex}")
            Text(text = "WIS: ${character.stats.wis}")
            Text(text = "CON: ${character.stats.con}")
            Text(text = "CHA: ${character.stats.cha}")
        }
    }
}
