package edu.rit.kc2736.simple5e.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.rit.kc2736.simple5e.ContentWrapper
import edu.rit.kc2736.simple5e.ViewModels.FeatureListViewModel
import edu.rit.kc2736.simple5e.ViewModels.SpellListViewModel
import edu.rit.kc2736.simple5e.databse.ApiReference
import edu.rit.kc2736.simple5e.databse.Feature
import edu.rit.kc2736.simple5e.ui.theme.EZ5eDarkTheme


@Composable
fun FeatureDetailView(viewModel: FeatureListViewModel, navController: NavController, index: String) {
    val feature = viewModel.featureDetail.value
    val scrollState = rememberScrollState()

    LaunchedEffect(index) {
        viewModel.getFeatureDetail(index)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top
    ) {
        feature?.let {
            FeatureDetailItem(it)
        }//END feature
    }
}

@Composable
fun FeatureDetailItem(feature: Feature) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = feature.name,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        FeatureLabelAndContent(label = "Class:", content = feature.featureClass?.name)
        FeatureLabelAndContent(label = "Subclass:", content = feature.subclass?.name)
        FeatureLabelAndContent(label = "Parent:", content = feature.parent?.name)

        feature.desc.forEach { description ->
            Text(
                text = description,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
        }

        FeatureLabelAndContent(label = "Level:", content = feature.level?.toString())

        feature.prerequisites.takeIf { it.isNotEmpty() }?.let { prerequisites ->
            Text(
                text = "Prerequisites:",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            prerequisites.forEach { prerequisite ->
                Text(
                    text = prerequisite.toString(), // Adjust this to display the prerequisite in a desired format
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                )
            }
        }

        feature.featureSpecific?.let { featureSpecific ->
            Text(
                text = "Feature-specific:",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            featureSpecific.forEach { (key, value) ->
                FeatureLabelAndContent(label = key, content = value.toString())
            }
        }
    }
}

@Composable
fun FeatureLabelAndContent(label: String, content: String?) {
    content?.let {
        Row(
            modifier = Modifier.padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary
            )
            Text(
                text = content,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EZ5eDarkTheme(true) {
        FeatureDetailItem(
            feature = Feature(
                index = "bardic-inspiration-d10",
                name = "Bardic Inspiration (d10)",
                url = "/api/features/bardic-inspiration-d10",
                desc = listOf("At 14th level, your Bardic Inspiration feature now gives your allies a d10 instead of a d8."),
                level = 14,
                featureClass = ApiReference(
                    index = "bard",
                    name = "Bard",
                    url = "/api/classes/bard"
                ),
                subclass = null,
                parent = null,
                prerequisites = emptyList(),
                featureSpecific = null
            )
        )
    }
}

