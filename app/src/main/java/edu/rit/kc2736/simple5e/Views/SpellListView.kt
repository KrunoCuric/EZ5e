package edu.rit.kc2736.simple5e.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import edu.rit.kc2736.simple5e.ViewModels.SpellListViewModel

@Composable
fun SpellListView(viewModel: SpellListViewModel){
    val stateTitle by viewModel.mutableTitle
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(textAlign = TextAlign.Center, text = stateTitle)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Button(onClick = { /*TODO*/ }) {

            }
        }
    }
}