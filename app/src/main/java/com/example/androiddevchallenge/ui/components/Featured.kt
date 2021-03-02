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
package com.example.androiddevchallenge.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.DogRepository
import com.example.androiddevchallenge.data.Skill
import com.example.androiddevchallenge.features.Navigator
import com.example.androiddevchallenge.features.Screen
import com.example.androiddevchallenge.ui.theme.DoggoTheme
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun FeaturedSection() {
    val dog = DogRepository.featuredDog
    val boxModifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .padding(horizontal = 32.dp)
    Box(modifier = boxModifier) {
        CoilImage(
            data = dog.imageUrl,
            contentDescription = "Featured Doggo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    Navigator.navigateTo(Screen.Profile(dog))
                }
        )
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(bottom = 12.dp, start = 12.dp)
                .align(Alignment.BottomStart)
                .alpha(.7f)
                .background(color = Color.Black, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = dog.name,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                color = Color.White
            )
        }
    }
}

@Composable
fun SkillTag(skill: Skill) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = skill.color,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Text(
            text = skill.title,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
            color = Color.White
        )
    }
}

enum class SelectableTagState {
    Selected,
    Unselected,
}

@Composable
fun SelectableSkillTag(skill: Skill) {
    val selected = remember { mutableStateOf(SelectableTagState.Unselected) }
    val transition = updateTransition(targetState = selected.value)
    val backgroundColor = transition.animateColor() { state ->
        when (state) {
            SelectableTagState.Selected -> skill.color
            SelectableTagState.Unselected -> Color.Unspecified
        }
    }
    val textColor = transition.animateColor { state ->
        when (state) {
            SelectableTagState.Selected -> Color.White
            SelectableTagState.Unselected -> skill.color
        }
    }

    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = backgroundColor.value,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 2.dp,
                color = skill.color,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable() {
                selected.value = when (selected.value) {
                    SelectableTagState.Selected -> SelectableTagState.Unselected
                    else -> SelectableTagState.Selected
                }
            }
    ) {
        Text(
            text = skill.title,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
            color = textColor.value
        )
    }
}

@Preview
@Composable
fun FeaturedFooderPreview() {
    DoggoTheme {
        FeaturedSection()
    }
}
