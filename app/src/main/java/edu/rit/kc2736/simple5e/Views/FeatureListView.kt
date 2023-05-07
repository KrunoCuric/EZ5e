package edu.rit.kc2736.simple5e.Views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.rit.kc2736.simple5e.Factories.Screen
import edu.rit.kc2736.simple5e.ViewModels.FeatureListViewModel

@Composable
fun FeatureListView(viewModel: FeatureListViewModel, navController: NavController){
    LaunchedEffect(Unit, block = {
        viewModel.getFeatureList()
    })
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(Modifier.fillMaxWidth()) {
            Text(
                text = "Features",
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterStart)
            )
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items = viewModel.featureList) { feature ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .clickable(onClick = {
                            val route = Screen.FeatureDetail.route.replace("{featureId}", feature.index)
                            navController.navigate(route)
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
                                Icons.Filled.FactCheck,
                                contentDescription = "feature",
                                tint = Color.Yellow,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(Modifier.width(16.dp))
                            Text(
                                text = feature.name,
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


