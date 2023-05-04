package edu.rit.kc2736.simple5e.Views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import edu.rit.kc2736.simple5e.ViewModels.MainViewModel

@Composable
fun MainView(viewModel: MainViewModel) {
    val stateString by viewModel.testString

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "EZ5e - MainMenu",
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
            Text(text = stateString, modifier = Modifier.padding(16.dp))

        }
        Row {
            Button(onClick = { viewModel.goToScreen1(stateString) }) {
                Text(text = "Spells")
            }
        }
        Row {
            Button(onClick = { viewModel.goToScreen2(stateString) }) {
                Text(text = "Go to screen2")
            }
        }
    }
}