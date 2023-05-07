package edu.rit.kc2736.simple5e.ViewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.rit.kc2736.simple5e.api.APIService
import edu.rit.kc2736.simple5e.databse.Spell
import edu.rit.kc2736.simple5e.databse.SpellDetail
import kotlinx.coroutines.launch
import retrofit2.http.GET
import retrofit2.http.Path

class SpellListViewModel: ViewModel() {
    private val _spellList = mutableStateListOf<Spell>()

    var errorMessage: String by mutableStateOf("")
    val spellList: List<Spell>
        get() = _spellList

    private val _spellDetail = mutableStateOf<SpellDetail?>(null)
    val spellDetail: SpellDetail?
        get()= _spellDetail.value
    fun getSpellList() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                _spellList.clear()
                _spellList.addAll(apiService.getSpells().results)
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
                _spellDetail.value = spellDetails
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }


}