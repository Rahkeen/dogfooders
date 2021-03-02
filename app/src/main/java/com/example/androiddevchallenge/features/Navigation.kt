/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.features

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.androiddevchallenge.data.Dog

sealed class Screen {
    object MarketPlace : Screen()
    data class Profile(val dog: Dog) : Screen()
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
