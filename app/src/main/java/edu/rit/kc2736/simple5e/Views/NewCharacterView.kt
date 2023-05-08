package edu.rit.kc2736.simple5e.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import edu.rit.kc2736.simple5e.ViewModels.CharacterViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import edu.rit.kc2736.simple5e.Factories.CharacterViewModelFactory
import edu.rit.kc2736.simple5e.databse.Alignment
import edu.rit.kc2736.simple5e.databse.Stats
import kotlin.random.Random


@Composable
fun NewCharacterView(navController: NavController) {
    val localContext = LocalContext.current
    val viewModel: CharacterViewModel = viewModel(factory = CharacterViewModelFactory(localContext))
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var backstory by remember { mutableStateOf("") }
    var values by remember { mutableStateOf("") }
    var bonds by remember { mutableStateOf("") }
    var selectedAlignmentIndex by remember { mutableStateOf(0) }
    var selectedRaceIndex by remember { mutableStateOf(0) }
    var selectedClassIndex by remember { mutableStateOf(0) }

    val races = listOf(
        "dragonborn", "dwarf", "elf", "gnome", "half-elf", "half-orc", "halfling", "human", "tiefling"
    )
    val classes = listOf(
        "barbarian", "bard", "cleric", "druid", "fighter", "monk", "paladin", "ranger", "rogue", "sorcerer", "warlock", "wizard"
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("New Character") })
        },
        content = {padding->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),

            ) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = age,
                    onValueChange = { age = it },
                    label = { Text("Age") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                DropdownMenu(
                    selectedItem = races[selectedRaceIndex],
                    items = races,
                    onItemSelected = { index -> selectedRaceIndex = index }
                )
                DropdownMenu(
                    selectedItem = classes[selectedClassIndex],
                    items = classes,
                    onItemSelected = { index -> selectedClassIndex = index }
                )
                DropdownMenu(
                    selectedItem = Alignment.values()[selectedAlignmentIndex].name,
                    items = Alignment.values().map { it.name }.toList(),
                    onItemSelected = { index -> selectedAlignmentIndex = index }
                )
                OutlinedTextField(
                    value = backstory,
                    onValueChange = { backstory = it },
                    label = { Text("Backstory") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    maxLines = 5
                )
                TextField(
                    value = values,
                    onValueChange = { values = it },
                    label = { Text("Values") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = bonds,
                    onValueChange = { bonds = it },
                    label = { Text("Bonds") },
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Stats (Randomly generated)",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                val randomStats = Stats(
                    str = Random.nextInt(8, 21),
                    int = Random.nextInt(8, 21),
                    dex = Random.nextInt(8, 21),
                    wis = Random.nextInt(8, 21),
                    con = Random.nextInt(8, 21),
                    cha = Random.nextInt(8, 21)
                )
                Text(
                    text = randomStats.toString(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Button(onClick = {
                    viewModel.createNewCharacter(
                        name, age.toIntOrNull(), races[selectedRaceIndex], classes[selectedClassIndex],
                        Alignment.values()[selectedAlignmentIndex], backstory, values, bonds, randomStats
                    )
                    viewModel.loadCharModels()
                    navController.navigate("characters")
                }) {
                    Text("Create Character")
                }
            }
        }
    )
}


@Composable
fun DropdownMenu(
    selectedItem: String,
    items: List<String>,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedIndex = items.indexOf(selectedItem)

    Box(modifier = modifier) {
        Button(onClick = { expanded = !expanded }) {
            Row {
                Text(text = selectedItem)
                Icon(Icons.Filled.ArrowDropDown, contentDescription = "Expand Dropdown")
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
//            LazyColumn {
                items.forEach() { item ->
                    Column {
                        DropdownMenuItem(onClick = {
                            expanded = false
                            onItemSelected(items.indexOf(item))
                        }) {
                            Text(text = item)
                        }
                    }
                }
//            }
        }
    }
}
