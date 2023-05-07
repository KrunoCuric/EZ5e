package edu.rit.kc2736.simple5e.Views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.rit.kc2736.simple5e.ViewModels.SpellListViewModel
import edu.rit.kc2736.simple5e.databse.SpellDetail
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SpellDetailView(viewModel: SpellListViewModel, navController: NavController) {
    val spellIndex = remember { navController.previousBackStackEntry?.savedStateHandle?.getLiveData<String>("spellIndex")?.value }

    LaunchedEffect(spellIndex) {
        spellIndex?.let {
            viewModel.getSpellDetail(it)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        LazyColumn(){
            item(viewModel.spellDetail){
                val spell = viewModel.spellDetail
                spell?.let {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Level ${it.level} ${it.school.name}",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Casting Time: ${it.castingTime}",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Range: ${it.range}",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Components: ${it.components.joinToString(separator = ", ")}",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Duration: ${it.duration}",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Description:",
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold
                    )
                    it.desc.forEach { desc ->
                        Text(
                            text = desc,
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    if (it.higherLevel.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "At Higher Levels:",
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold
                        )
                        it.higherLevel.forEach { higherLevel ->
                            Text(
                                text = higherLevel,
                                style = MaterialTheme.typography.body1,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }

}
