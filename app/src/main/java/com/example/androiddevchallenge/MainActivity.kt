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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.androiddevchallenge.ui.components.Header
import com.example.androiddevchallenge.ui.theme.DoggoTheme
import com.example.androiddevchallenge.ui.theme.doggoGray
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

val doggos = listOf(
    1,
    2,
    3,
    4,
    5,
    6
)

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
}

@Composable
fun DoggoApp() {
    Column(modifier = Modifier.fillMaxSize()) {
        Header()
        Spacer(modifier = Modifier.height(16.dp))
        DogStore()
    }
}

@Composable
fun DogStore() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = doggos) { dog ->
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp)
                    .background(
                        color = doggoGray,
                        shape = RoundedCornerShape(16.dp)
                    )
            )
        }
    }
}

@Preview("Light Theme", showSystemUi = true)
@Composable
fun LightPreview() {
    DoggoTheme {
        DoggoApp()
    }
}

@Preview("Dark Theme", showSystemUi = true)
@Composable
fun DarkPreview() {
    DoggoTheme(darkTheme = true) {
        DoggoApp()
    }
}
