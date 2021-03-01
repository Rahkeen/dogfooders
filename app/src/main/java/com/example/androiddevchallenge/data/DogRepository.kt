package com.example.androiddevchallenge.data

import androidx.compose.ui.graphics.Color
import com.example.androiddevchallenge.data.Skill.Fetch
import com.example.androiddevchallenge.data.Skill.Sit
import com.example.androiddevchallenge.data.Skill.Stay
import com.example.androiddevchallenge.ui.theme.doggoBackground
import com.example.androiddevchallenge.ui.theme.doggoBlue
import com.example.androiddevchallenge.ui.theme.doggoGreen
import com.example.androiddevchallenge.ui.theme.doggoOrange
import com.example.androiddevchallenge.ui.theme.doggoRed

data class Dog(
    val name: String,
    val imageUrl: String,
    val description: String,
    val rating: Float,
    val hourlyRate: Int,
    val skills: List<Skill>
)

enum class Skill(val color: Color, val title: String) {
    Fetch(color = doggoRed, title = "Fetch"),
    RollOver(color = doggoBlue, title = "Roll Over"),
    Sit(color = doggoGreen, title = "Sit"),
    Stay(color = doggoOrange, title = "Stay"),
}

object DogRepository {
    val doggies = listOf(
        JEFF,
        BOB,
        SARAH,
        KATE
    )

    val featuredDog = BOB
}

val JEFF = Dog(
    name = "Jeff",
    imageUrl = "https://images.unsplash.com/photo-1560115316-b1cb0591b631?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw" +
            "%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=2851&q=80",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut " +
            "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    rating = 5F,
    hourlyRate = 30,
    skills = listOf(Fetch, Sit, Stay)
)
val BOB = Dog(
    name = "Bob",
    imageUrl = "https://images.unsplash" +
            ".com/photo-1583512603805-3cc6b41f3edb?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=2000&q=80",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut.",
    rating = 5F,
    hourlyRate = 30,
    skills = listOf(Fetch, Sit, Stay)
)
val SARAH = Dog(
    name = "Sarah",
    imageUrl = "https://images.unsplash.com/photo-1543466835-00a7907e9de1?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw" +
            "%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=2467&q=80",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut " +
            "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    rating = 5F,
    hourlyRate = 30,
    skills = listOf(Fetch, Sit, Stay)
)
val KATE = Dog(
    name = "Kate",
    imageUrl = "https://images.unsplash.com/photo-1553882809-a4f57e59501d?ixlib=rb-1.2" +
            ".1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1234&q=80",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut " +
            "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    rating = 5F,
    hourlyRate = 30,
    skills = listOf(Fetch, Sit, Stay)
)