package com.example.androiddevchallenge.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

sealed class Screen {
    object MarketPlace: Screen()
    data class Profile(val dog: Dog): Screen()
}

object Navigator {
    var currentScreen by mutableStateOf<Screen>(Screen.MarketPlace)

    fun navigateTo(screen: Screen) {
        currentScreen = screen
    }

    fun onBackPressed(): Boolean {
        return if (currentScreen is Screen.MarketPlace) {
            false
        } else {
            currentScreen = Screen.MarketPlace
            true
        }
    }
}
