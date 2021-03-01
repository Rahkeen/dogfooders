package com.example.androiddevchallenge.features

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.DogRepository
import com.example.androiddevchallenge.ui.theme.doggoGreen
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun DogProfile() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val dog = DogRepository.featuredDog
            CoilImage(
                data = dog.imageUrl,
                contentDescription = "Doggo",
                modifier = Modifier
                    .width(150.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(16.dp))
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
                    modifier = Modifier.fillMaxSize().weight(1F),
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
    }
}