package edu.rit.kc2736.simple5e.ViewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.rit.kc2736.simple5e.api.APIService
import edu.rit.kc2736.simple5e.databse.ApiReference
import edu.rit.kc2736.simple5e.databse.Feature
import kotlinx.coroutines.launch

class FeatureListViewModel: ViewModel() {
    private val _featureList = mutableStateListOf<ApiReference>()

    var errorMessage: String by mutableStateOf("")
    val featureList: List<ApiReference>
        get() = _featureList

    var featureDetail: MutableState<Feature?> = mutableStateOf(null)
    fun getFeatureList() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                _featureList.clear()
                _featureList.addAll(apiService.getFeatures().results)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun getFeatureDetail(index: String) {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                val data = apiService.getFeature(index)
                featureDetail.value = data
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}