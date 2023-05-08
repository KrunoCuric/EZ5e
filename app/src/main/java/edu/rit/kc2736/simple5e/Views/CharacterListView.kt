package edu.rit.kc2736.simple5e.Views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import edu.rit.kc2736.simple5e.Factories.CharacterViewModelFactory
import edu.rit.kc2736.simple5e.ViewModels.CharacterViewModel
import edu.rit.kc2736.simple5e.databse.CharModel
import edu.rit.kc2736.simple5e.databse.getCharListFilePath
import java.net.URLEncoder


@Composable
fun CharacterListView(navController: NavController) {
    val localContext = LocalContext.current
    val viewModel: CharacterViewModel = viewModel(factory = CharacterViewModelFactory(localContext))
    LaunchedEffect(Unit, block = {
        viewModel.loadCharModels()
    })
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Characters") })
        },
        content = {padding->
//            Row(modifier= Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState()),
//                ) {
                    LazyColumn(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(viewModel.charModels.value) { charModel ->
                            CharacterListItem(charModel) {
                                // On click, load character and navigate to CharacterView
                                val encodedPath = URLEncoder.encode(viewModel.getPath(charModel), "UTF-8")
                                navController.navigate("characterDetail/${encodedPath}")
                            }
                        }
                    }
//            }
        }
    )
}

@Composable
fun CharacterListItem(charModel: CharModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = charModel.name, style = MaterialTheme.typography.h6)
            Text(text = "Race: ${charModel.race}")
            Text(text = "Class: ${charModel.charClass}")
            Text(text = "Level: ${charModel.level}")
        }
    }
}
