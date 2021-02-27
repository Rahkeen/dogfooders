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
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.androiddevchallenge.data.DogRepository
import com.example.androiddevchallenge.ui.components.Header
import com.example.androiddevchallenge.ui.theme.DoggoTheme
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

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
    val animation = rememberInfiniteTransition()
    val rotationState = animation.animateFloat(
        initialValue = 0F, targetValue = 180F, animationSpec = InfiniteRepeatableSpec(
            animation = tween(durationMillis = 1000, delayMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    )
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(items = DogRepository.doggies) { index, dogUrl ->

            Box(modifier = Modifier
                .wrapContentSize()
                .graphicsLayer(rotationY = rotationState.value)
            ) {
                CoilImage(
                    data = dogUrl,
                    contentDescription = "Doggo Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(200.dp)
                        .height(300.dp)
                        .clip(RoundedCornerShape(16.dp))
                        ,
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .wrapContentSize()
                        .padding(bottom = 8.dp, end = 8.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Text(
                        text = "Dog Name",
                        modifier = Modifier
                            .padding(8.dp),
                    )
                }
            }
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
