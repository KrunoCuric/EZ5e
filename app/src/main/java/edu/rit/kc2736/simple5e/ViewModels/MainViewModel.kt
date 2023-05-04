package edu.rit.kc2736.simple5e.ViewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var testString = mutableStateOf("Initial Value")

    fun goToScreen1(stateString: String) {
        testString.value = "goToScreen1 tapped!"
    }

    fun goToScreen2(passedValue: String){
        testString.value = "goToScreen2 tapped!"
    }

}