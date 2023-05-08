package edu.rit.kc2736.simple5e.databse

//Simplified character with path to json file
data class CharModel(
    val name: String,
    val race: String,
    val charClass: String,
    val level: Int,
    val filePath: String
)


// Enum for Alignment
enum class Alignment {
    LAWFUL_GOOD,
    NEUTRAL_GOOD,
    CHAOTIC_GOOD,
    LAWFUL_NEUTRAL,
    NEUTRAL,
    CHAOTIC_NEUTRAL,
    LAWFUL_EVIL,
    NEUTRAL_EVIL,
    CHAOTIC_EVIL
}

// Data class for Stats
data class Stats(
    val str: Int,
    val int: Int,
    val dex: Int,
    val wis: Int,
    val con: Int,
    val cha: Int
)

// Data class for Character Model
data class Character(
    val name: String,
    val age: Int,
    val race: ApiReference,
    val characterClass: ApiReference,
    val level: Int = 1,
    val alignment: Alignment,
    val backstory: String,
    val values: String,
    val bonds: String,
    val stats: Stats
)

// Data class for Race Model
data class Race(
    val name: String,
    val speed: Int,
    val size: String,
    val language_desc: String
)

// Data class for Class Model
data class CharacterClass(
    val name: String,
    val hit_dice: Int
)

