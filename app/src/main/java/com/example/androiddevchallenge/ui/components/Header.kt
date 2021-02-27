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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.doggoYellow
import com.example.androiddevchallenge.ui.theme.typography
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.statusBarsPadding

data class HeaderState(val imageUrl: String? = null)

@ExperimentalAnimationApi
@Composable
fun Header(state: HeaderState) {
    Column(
        modifier = Modifier
            .background(
                color = doggoYellow,
                shape = RoundedCornerShape(
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )
            .fillMaxWidth()
            .fillMaxHeight(.3F)
            .statusBarsPadding()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Find a friend",
            color = Color.White,
            style = typography.h1
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            this@Column.AnimatedVisibility(
                visible = state.imageUrl != null,
                enter = expandIn(expandFrom = Alignment.Center),
                exit = shrinkOut(shrinkTowards = Alignment.Center)
            ) {
                CoilImage(
                    data = state.imageUrl!!,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(50.dp)
                        .height(75.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview(showSystemUi = true)
@Composable
fun HeaderPreview() {
    MaterialTheme {
        Header(HeaderState())
    }
}
