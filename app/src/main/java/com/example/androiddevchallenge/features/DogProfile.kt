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

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.TopBar
import com.example.androiddevchallenge.data.Dog
import com.example.androiddevchallenge.data.Skill
import com.example.androiddevchallenge.ui.theme.doggoBlue
import com.example.androiddevchallenge.ui.theme.doggoGreen
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun DogProfile(dog: Dog) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TopBar()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CoilImage(
                data = dog.imageUrl,
                contentDescription = "Doggo",
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = dog.name,
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier.padding(
                            vertical = 8.dp,
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1F),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1F)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${dog.rating}",
                            style = MaterialTheme.typography.h3,
                            modifier = Modifier.padding(
                                vertical = 8.dp,
                                horizontal = 16.dp
                            )
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1F)
                            .background(
                                color = doggoGreen,
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$${dog.hourlyRate}",
                            style = MaterialTheme.typography.h3,
                            color = Color.White,
                            modifier = Modifier.padding(
                                vertical = 8.dp,
                                horizontal = 16.dp
                            )
                        )
                    }
                }
            }
        }

        // Skills Module
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                dog.skills.forEach {
                    SkillDisplay(skill = it)
                }
            }
        }

        // Description
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = dog.description,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(12.dp)
            )
        }

        // Buttons
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ProfileButton(text = "Back", action = { Navigator.onBackPressed() })
                ProfileButton(text = "Hire", textColor = Color.White, backgroundColor = doggoBlue)
            }
        }
    }
}

@Composable
fun SkillDisplay(skill: Skill) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = skill.color,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = skill.title,
            style = MaterialTheme.typography.body1,
            color = Color.White,
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 16.dp
            )
        )
    }
}

@Composable
fun ProfileButton(
    text: String,
    action: () -> Unit = {},
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.White
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = action
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h4,
            color = textColor,
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 32.dp
            )
        )
    }
}
