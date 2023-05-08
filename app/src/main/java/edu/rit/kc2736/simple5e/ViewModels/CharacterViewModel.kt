package edu.rit.kc2736.simple5e.ViewModels

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.rit.kc2736.simple5e.api.APIService
import edu.rit.kc2736.simple5e.databse.Alignment
import edu.rit.kc2736.simple5e.databse.ApiReference
import edu.rit.kc2736.simple5e.databse.CharModel
import edu.rit.kc2736.simple5e.databse.Character
import edu.rit.kc2736.simple5e.databse.CharacterClass
import edu.rit.kc2736.simple5e.databse.Race
import edu.rit.kc2736.simple5e.databse.Stats
import edu.rit.kc2736.simple5e.databse.characterFileName
import edu.rit.kc2736.simple5e.databse.getCharListFilePath
import edu.rit.kc2736.simple5e.databse.readCharModelsFromFile
import edu.rit.kc2736.simple5e.databse.readCharModelsFromFileWithContext
import edu.rit.kc2736.simple5e.databse.readCharacterFromFile
import edu.rit.kc2736.simple5e.databse.readCharacterFromFileWithoutContext
import edu.rit.kc2736.simple5e.databse.writeCharModelsToFile
import edu.rit.kc2736.simple5e.databse.writeCharacterToFile
import kotlinx.coroutines.launch
import java.io.File
import java.util.Locale


class CharacterViewModel(private val context: Context) : ViewModel() {
    private val apiService = APIService.getInstance()
    val charModels = mutableStateOf<List<CharModel>>(emptyList())
    private var currentCharacter: Character? = null
    val loadedCharacter: MutableState<Character?> = mutableStateOf(null)
    fun loadCharModels() {
        viewModelScope.launch {
            val filePath = getCharListFilePath(context)
            charModels.value = readCharModelsFromFile(filePath)
        }
    }
    fun fetchRace(raceId: String, onResult: (Race?) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.getRace(raceId)
                if (response.isSuccessful) {
                    onResult(response.body())
                } else {
                    onResult(null)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(null)
            }
        }
    }
    fun fetchClass(classId: String, onResult: (CharacterClass?) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.getClass(classId)
                if (response.isSuccessful) {
                    onResult(response.body())
                } else {
                    onResult(null)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(null)
            }
        }
    }
    fun loadCharacter(charModel: CharModel) {
        viewModelScope.launch {
            val filePath = "${context.filesDir}/${characterFileName(charModel)}"
            //currentCharacter = readCharacterFromFile(context, filePath)
            currentCharacter = readCharacterFromFileWithoutContext(characterFileName(charModel))
            if(currentCharacter != null){
                loadedCharacter.value = currentCharacter
            }
        }
    }
    fun loadCharacterWithPath(charPath: String) {
        viewModelScope.launch {
            currentCharacter = readCharacterFromFileWithoutContext(charPath)
            if(currentCharacter != null){
                loadedCharacter.value = currentCharacter
            }
        }
    }
    fun deleteCharacter(charModel: CharModel) {
        viewModelScope.launch {
            // Delete the character JSON file
            val charFilePath = "${context.filesDir}/${characterFileName(charModel)}"
            File(charFilePath).delete()
            // Remove the CharModel from the list and update char_list.json
            val updatedCharModels = charModels.value.filter { it != charModel }
            charModels.value = updatedCharModels
            val charListFilePath = getCharListFilePath(context)
            writeCharModelsToFile(context, charListFilePath, updatedCharModels)
        }
    }
    fun createNewCharacter(
        name: String,
        age: Int?,
        raceName: String,
        className: String,
        alignment: Alignment,
        backstory: String,
        values: String,
        bonds: String,
        randomStats: Stats
    ) {
        viewModelScope.launch {
            val race = ApiReference(index = raceName, name = raceName.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }, url = "/api/races/$raceName")

            val characterClass = ApiReference(index = className, name = className.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }, url = "/api/classes/$className")
            val newCharacter = Character(
                name = name,
                age = age ?: 1,
                race = race,
                characterClass = characterClass,
                level = 1,
                alignment = alignment,
                backstory = backstory,
                values = values,
                bonds = bonds,
                stats = randomStats
            )
            loadedCharacter.value = newCharacter
            // Write the new character to a file
            val characterFilePath = characterFileName(newCharacter)
            writeCharacterToFile(context, characterFilePath, newCharacter)
            // Create a new CharModel object with the generated file path
            val newCharModel = CharModel(
                name = name,
                race = raceName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                charClass = className.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                level = 1,
                filePath = characterFilePath
            )
            // Add the new CharModel to the list and write it to the char_list.json file
            val charModelsList = readCharModelsFromFileWithContext(context,"char_list.json").toMutableList()
            charModelsList.add(newCharModel)
            writeCharModelsToFile(context,"char_list.json", charModelsList)
            // Update the character list
            charModels.value = charModelsList
        }
    }

    fun getPath(charModel: CharModel): String {
        return "${context.filesDir}/${characterFileName(charModel)}"
    }

}
