package edu.rit.kc2736.simple5e.databse

data class Spells(
    var count: Int,
    var results: List<ApiReference>
)

data class ApiReference(
    var index: String,
    var name: String,
    var url: String
)

data class SpellDetail(
    val index: String,
    val name: String,
    val desc: List<String>,
    val higherLevel: List<String>,
    val range: String,
    val components: List<String>,
    val material: String?,
    val ritual: Boolean,
    val duration: String,
    val concentration: Boolean,
    val castingTime: String,
    val level: Int,
    val school: SpellSchool,
    val classes: List<SpellClass>,
    val subclasses: List<SpellSubclass>
)

data class SpellSchool(
    val name: String,
    val url: String
)

data class SpellClass(
    val name: String,
    val url: String
)

data class SpellSubclass(
    val name: String,
    val url: String
)


