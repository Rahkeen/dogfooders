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

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.TopBar
import com.example.androiddevchallenge.data.DogRepository
import com.example.androiddevchallenge.data.Skill
import com.example.androiddevchallenge.data.UpdateSkill
import com.example.androiddevchallenge.ui.components.FeaturedSection
import com.example.androiddevchallenge.ui.components.SelectableTagState
import dev.chrisbanes.accompanist.coil.CoilImage

@ExperimentalFoundationApi
@Composable
fun DogMarketplace() {
    var selectedSkills by remember { mutableStateOf(listOf<Skill>()) }

    fun updateSkills(action: UpdateSkill) {
        selectedSkills = when (action) {
            is UpdateSkill.Add -> selectedSkills + action.skill
            is UpdateSkill.Remove -> selectedSkills - action.skill
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar()
        HeaderText(header = "Featured Fooder")
        FeaturedSection()
        Spacer(modifier = Modifier.height(16.dp))
        SkillSection(::updateSkills)
        Spacer(modifier = Modifier.height(16.dp))
        DogFeed(selectedSkills)
    }
}

@Composable
fun HeaderText(header: String) {
    Text(
        text = header,
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp),
        style = MaterialTheme.typography.h3
    )
}

@Composable
fun SkillSection(
    action: (UpdateSkill) -> Unit
) {
    val containerModifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .padding(horizontal = 32.dp)

    fun shapeForIndex(index: Int, radius: Dp): Shape {
        return when (index) {
            0 -> {
                RoundedCornerShape(topStart = radius, bottomStart = radius)
            }
            Skill.values().lastIndex -> {
                RoundedCornerShape(topEnd = radius, bottomEnd = radius)
            }
            else -> {
                RectangleShape
            }
        }
    }

    Row(
        modifier = containerModifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Skill.values().forEachIndexed { index, skill ->
            var selectedState by remember(index) { mutableStateOf(SelectableTagState.Unselected) }
            val transition = updateTransition(targetState = selectedState)
            val verticalPadding by transition.animateDp { state ->
                when (state) {
                    SelectableTagState.Unselected -> 4.dp
                    SelectableTagState.Selected -> 0.dp
                }
            }

            val horizontalPadding by transition.animateDp { state ->
                when (state) {
                    SelectableTagState.Unselected -> 2.dp
                    SelectableTagState.Selected -> 0.dp
                }
            }

            val backgroundColor by transition.animateColor { state ->
                when (state) {
                    SelectableTagState.Unselected -> skill.altColor
                    SelectableTagState.Selected -> skill.color
                }
            }

            val textColor by transition.animateColor { state ->
                when (state) {
                    SelectableTagState.Unselected -> Color.Black
                    SelectableTagState.Selected -> Color.White
                }
            }

            val shapeRadius by transition.animateDp { state ->
                when (state) {
                    SelectableTagState.Unselected -> 12.dp
                    SelectableTagState.Selected -> 16.dp
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1F)
                    .padding(vertical = verticalPadding, horizontal = horizontalPadding)
                    .background(
                        color = backgroundColor,
                        shape = shapeForIndex(index, shapeRadius)
                    )
                    .clip(shape = shapeForIndex(index, shapeRadius))
                    .clickable {
                        selectedState = when (selectedState) {
                            SelectableTagState.Unselected -> {
                                action(UpdateSkill.Add(skill))
                                SelectableTagState.Selected
                            }
                            else -> {
                                action(UpdateSkill.Remove(skill))
                                SelectableTagState.Unselected
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = skill.title, color = textColor)
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun DogFeed(skills: List<Skill>) {
    val dogs = DogRepository.filteredDoggies(skills)
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
    ) {
        items(count = dogs.size) { index ->
            val dog = dogs[index]
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(4.dp)
            ) {
                CoilImage(
                    data = dog.imageUrl,
                    contentDescription = "Doggo Image",
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
                        .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = dog.name,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                        color = Color.Black
                    )
                }
            }
        }
    }
}
