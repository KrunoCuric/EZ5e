package edu.rit.kc2736.simple5e.databse

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.util.Locale

fun readCharModelsFromFile(filePath: String): List<CharModel> {
//    val file = File(context.getExternalFilesDir(null),filePath)
    val file = File(filePath)
    if (!file.exists()) {
        return emptyList()
    }

    val gson = Gson()
    val type = object : TypeToken<List<CharModel>>() {}.type
    val jsonString = file.readText()
    return gson.fromJson(jsonString, type)
}
fun readCharModelsFromFileWithContext(context: Context, filePath: String): List<CharModel> {
    val file = File(context.getExternalFilesDir(null),filePath)
//    val file = File(filePath)
    if (!file.exists()) {
        return emptyList()
    }

    val gson = Gson()
    val type = object : TypeToken<List<CharModel>>() {}.type
    val jsonString = file.readText()
    return gson.fromJson(jsonString, type)
}

fun writeCharModelsToFile(context: Context, filePath: String, charModels: List<CharModel>) {
    val gson = Gson()
    val jsonString = gson.toJson(charModels)
    File(context.getExternalFilesDir(null),filePath).writeText(jsonString)
//    File(filePath).writeText(jsonString)
}
fun characterFileName(character: Character): String {
    return "${character.name.lowercase(Locale.ROOT).replace("\\s".toRegex(), "")}_${character.race.name.lowercase(Locale.ROOT).replace("\\s".toRegex(), "")}_${
        character.characterClass.name.lowercase(Locale.ROOT).replace("\\s".toRegex(), "")
    }.json"

}
fun characterFileName(character: CharModel): String {
    return "${character.name.lowercase(Locale.ROOT).replace("\\s".toRegex(), "")}_${character.race.lowercase(Locale.ROOT).replace("\\s".toRegex(), "")}_${
        character.charClass.lowercase(Locale.ROOT).replace("\\s".toRegex(), "")
    }.json"

}

fun readCharacterFromFile(context: Context, filePath: String): Character? {
//    val file = File(context.getExternalFilesDir(null), filePath)
    val file = File(filePath)
    if (!file.exists()) {
        return null
    }

    val gson = Gson()
    val jsonString = file.readText()
    return gson.fromJson(jsonString, Character::class.java)
}
fun readCharacterFromFileWithoutContext(filePath: String): Character? {
//    val file = File(context.getExternalFilesDir(null), filePath)
    val file = File(filePath)
    if (!file.exists()) {
        return null
    }

    val gson = Gson()
    val jsonString = file.readText()
    return gson.fromJson(jsonString, Character::class.java)
}

fun writeCharacterToFile(context:Context, filePath: String, character: Character) {
    val gson = Gson()
    val jsonString = gson.toJson(character)
    File(context.getExternalFilesDir(null),filePath).writeText(jsonString)
}
fun getCharListFilePath(context: Context): String {
    return File(context.getExternalFilesDir(null), "char_list.json").absolutePath
}

fun createInitialCharModelFile(context: Context) {
    val filePath = getCharListFilePath(context)
    val file = File(filePath)

    if (!file.exists()) {
        File(filePath).writeText("[]")
    }
}
