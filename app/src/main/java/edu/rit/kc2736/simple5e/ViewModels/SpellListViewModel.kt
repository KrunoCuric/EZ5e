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
import edu.rit.kc2736.simple5e.databse.SpellDetail
import kotlinx.coroutines.launch

class SpellListViewModel: ViewModel() {
    private val _apiReferenceList = mutableStateListOf<ApiReference>()

    var errorMessage: String by mutableStateOf("")
    val apiReferenceList: List<ApiReference>
        get() = _apiReferenceList

    var spellDetail: MutableState<SpellDetail?> = mutableStateOf(null)
//    val spellDetail: LiveData<SpellDetail> = _spellDetail
    fun getSpellList() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                _apiReferenceList.clear()
                _apiReferenceList.addAll(apiService.getSpells().results)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun getSpellDetail(index: String) {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                val spellDetails = apiService.getSpell(index)
                spellDetail.value = spellDetails
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }



}