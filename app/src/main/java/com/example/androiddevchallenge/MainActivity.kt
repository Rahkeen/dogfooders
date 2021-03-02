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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.androiddevchallenge.data.Navigator
import com.example.androiddevchallenge.data.Screen
import com.example.androiddevchallenge.features.DogMarketplace
import com.example.androiddevchallenge.features.DogProfile
import com.example.androiddevchallenge.ui.theme.DoggoTheme
import com.example.androiddevchallenge.ui.theme.doggoBackground
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dev.chrisbanes.accompanist.insets.statusBarsHeight

@ExperimentalFoundationApi
@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            DoggoTheme {
                ProvideWindowInsets {
                    DoggoApp()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!Navigator.onBackPressed()) {
            super.onBackPressed()
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun DoggoApp() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = doggoBackground)
    ) {
        Crossfade(targetState = Navigator.currentScreen) { screen ->
            when(screen) {
                is Screen.MarketPlace -> {
                    DogMarketplace()
                }
                is Screen.Profile -> {
                    DogProfile(screen.dog)
                }
            }
        }
    }
}

@Composable
fun TopBar() {
    val topBarModifier = Modifier
        .fillMaxWidth()
        .statusBarsHeight()

    Box(modifier = topBarModifier)
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Preview("Light Theme", showSystemUi = true)
@Composable
fun LightPreview() {
    DoggoTheme {
        DoggoApp()
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Preview("Dark Theme", showSystemUi = true)
@Composable
fun DarkPreview() {
    DoggoTheme(darkTheme = true) {
        DoggoApp()
    }
}
