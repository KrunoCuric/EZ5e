package edu.rit.kc2736.simple5e.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.icons.Icons

import androidx.compose.material.Text
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import edu.rit.kc2736.simple5e.ViewModels.SpellListViewModel
import edu.rit.kc2736.simple5e.itembutton.ItemButton
import kotlin.reflect.KProperty

@Composable
fun SpellListView(viewModel: SpellListViewModel, navController: NavController){
    LaunchedEffect(Unit, block = {
        viewModel.getSpellList()
    })
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(Modifier.fillMaxWidth()) {
            Text(
                text = "Spells",
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterStart)
            )
        }
//        LazyColumn(modifier = Modifier.fillMaxSize()) {
//            items(items = viewModel.spellList) { spell ->
//                Row(
//                    modifier = Modifier.fillMaxWidth().padding(horizontal = 0.dp, vertical = 8.dp),
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    ItemButton(
//                        icon = {
//                            Icon(
//                                Icons.Filled.MenuBook,
//                                contentDescription = "magic",
//                                modifier = Modifier.fillMaxSize()
//                            )
//                        },
//                        text = spell.name,
//                        onClick = { viewModel.gotoDetail() }
//                    )
//                }
//            }
//        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items = viewModel.spellList) { spell ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .clickable(onClick = {
                            val bundle = bundleOf("spellIndex" to spell.index)
                            navController.previousBackStackEntry?.savedStateHandle?.set("spellIndex", bundle)
                            navController.navigate("spellDetail")
                        })
                        .shadow(2.dp)
                        .height(64.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 0.dp, horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Filled.MenuBook,
                                contentDescription = "magic",
                                tint = Color.Yellow,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(Modifier.width(16.dp))
                            Text(
                                text = spell.name,
                                style = MaterialTheme.typography.h6,
                                color = MaterialTheme.colors.onSurface
                            )
                        }
                        Icon(
                            Icons.Filled.ArrowForward,
                            contentDescription = "Go to details",
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }//LazyColumn END

    }

}


