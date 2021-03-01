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
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.androiddevchallenge.data.DogRepository
import com.example.androiddevchallenge.data.Skill
import com.example.androiddevchallenge.ui.components.FeaturedSection
import com.example.androiddevchallenge.ui.components.SkillTag
import com.example.androiddevchallenge.ui.theme.DoggoTheme
import com.example.androiddevchallenge.ui.theme.doggoBackground
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

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
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun DoggoApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = doggoBackground)
    ) {
        TopBar()
        FeaturedSection()
        Spacer(modifier = Modifier.height(16.dp))
        SkillSection()
        Spacer(modifier = Modifier.height(16.dp))
        DogFeed()
        Spacer(modifier = Modifier.height(16.dp))
        TopBar()
    }
}

@Composable
fun TopBar() {
    val topBarModifier = Modifier
        .fillMaxWidth()
        .height(100.dp)

    Box(modifier = topBarModifier)
}

@Composable
fun SkillSection() {
    val containerModifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .height(80.dp)
        .padding(horizontal = 32.dp)
        .background(color = Color.White, shape = RoundedCornerShape(16.dp))
    Row(
        modifier = containerModifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Skill.values().forEach { skill ->
           SkillTag(skill = skill)
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun DogFeed() {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 2.dp),
    ) {
       items(count = DogRepository.doggies.size) { index ->
           CoilImage(
               data = DogRepository.doggies[index].imageUrl,
               contentDescription = "Doggo Image",
               contentScale = ContentScale.Crop,
               modifier = Modifier
                   .fillMaxWidth()
                   .height(275.dp)
                   .padding(horizontal = 2.dp, vertical = 2.dp)
                   .clip(RoundedCornerShape(16.dp))
           )
       }
    }
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
