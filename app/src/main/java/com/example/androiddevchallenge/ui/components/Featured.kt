package com.example.androiddevchallenge.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.DogRepository
import com.example.androiddevchallenge.data.Skill
import com.example.androiddevchallenge.ui.theme.DoggoTheme
import com.example.androiddevchallenge.ui.theme.typography
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun FeaturedSection() {
    val dog = DogRepository.featuredDog
    val boxModifier = Modifier
        .fillMaxWidth()
        .height(250.dp)
    Box(modifier = boxModifier) {
        val contentModifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
        Row(modifier = contentModifier, verticalAlignment = Alignment.CenterVertically) {
            CoilImage(
                data = dog.imageUrl,
                contentDescription = "Featured Doggo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(150.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = dog.name,
                    color = Color.White,
                    style = typography.h1
                )

                Text(
                    text = dog.description,
                    color = Color.White,
                    style = typography.body1 
                )
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 8.dp), contentAlignment = Alignment
                    .BottomStart) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        dog.skills.forEach {
                            SkillTag(skill = it)
                        }
                    }
                }
            }
        }
        SectionTag(
            name = "Featured",
            modifier = Modifier
                .align(alignment = Alignment.TopStart),
            color = Color.DarkGray
        )
    }
}

@Composable
fun SectionTag(
    name: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified
) {
    Box(
        modifier = modifier.then(
            Modifier
                .wrapContentSize()
                .background(
                    color = color,
                    shape = RoundedCornerShape(4.dp)
                ))
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(4.dp),
            color = Color.White
        )
    }
}


@Composable
fun SkillTag(skill: Skill) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = skill.color,
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        Text(
            text = skill.title,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
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
        when(state) {
            SelectableTagState.Selected -> Color.White
            SelectableTagState.Unselected -> skill.color
        }
    }

    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = backgroundColor.value,
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = 2.dp,
                color = skill.color,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable {
                selected.value = when(selected.value) {
                    SelectableTagState.Selected -> SelectableTagState.Unselected
                    else -> SelectableTagState.Selected
                }
            }
    ) {
        Text(
            text = skill.title,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
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