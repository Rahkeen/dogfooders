package com.example.androiddevchallenge.features

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.TopBar
import com.example.androiddevchallenge.data.DogRepository
import com.example.androiddevchallenge.data.Navigator
import com.example.androiddevchallenge.data.Screen
import com.example.androiddevchallenge.data.Skill
import com.example.androiddevchallenge.ui.components.FeaturedSection
import com.example.androiddevchallenge.ui.components.SkillTag
import dev.chrisbanes.accompanist.coil.CoilImage

@ExperimentalFoundationApi
@Composable
fun DogMarketplace() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBar()
        FeaturedSection()
        Spacer(modifier = Modifier.height(16.dp))
        SkillSection()
        Spacer(modifier = Modifier.height(16.dp))
        DogFeed()
    }
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
            val dog = DogRepository.doggies[index]
            CoilImage(
                data = dog.imageUrl,
                contentDescription = "Doggo Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(275.dp)
                    .padding(horizontal = 2.dp, vertical = 2.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        Navigator.navigateTo(Screen.Profile(dog))
                    }
            )
        }
    }
}
