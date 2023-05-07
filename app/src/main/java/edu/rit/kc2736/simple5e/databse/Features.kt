package edu.rit.kc2736.simple5e.databse

data class Feature(
    val index: String,
    val name: String,
    val url: String,
    val desc: List<String>,
    val level: Int?,
    val featureClass: ApiReference?,
    val subclass: ApiReference?,
    val parent: ApiReference?,
    val prerequisites: List<Prerequisite>,
    val featureSpecific: Map<String, Any>?
)
data class Prerequisite(
    val type: String,
    val level: Int?,
    val feature: ApiReference?,
    val spell: ApiReference?
)
data class Features(
    var count: Int,
    var results: List<ApiReference>
)

