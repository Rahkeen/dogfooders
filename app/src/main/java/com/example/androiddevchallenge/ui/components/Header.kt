package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.doggoYellow
import com.example.androiddevchallenge.ui.theme.typography

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .background(
                color = doggoYellow,
                shape = RoundedCornerShape(
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )
            .fillMaxWidth()
            .fillMaxHeight(.3F),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Doggo",
            color = Color.White,
            style = typography.h1
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HeaderPreview() {
    MaterialTheme {
        Header()
    }
}

