package edu.rit.kc2736.simple5e.ViewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SpellListViewModel: ViewModel() {
    var mutableTitle = mutableStateOf("Spell List View")
}