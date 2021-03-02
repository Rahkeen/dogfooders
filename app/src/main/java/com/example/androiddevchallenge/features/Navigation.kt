package com.example.androiddevchallenge.features

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.androiddevchallenge.data.Dog

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
