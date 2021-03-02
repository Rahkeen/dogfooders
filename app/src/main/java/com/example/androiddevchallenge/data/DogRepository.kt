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
package com.example.androiddevchallenge.data

import androidx.compose.ui.graphics.Color
import com.example.androiddevchallenge.data.Skill.Fetch
import com.example.androiddevchallenge.data.Skill.Shake
import com.example.androiddevchallenge.data.Skill.Sit
import com.example.androiddevchallenge.data.Skill.Stay
import com.example.androiddevchallenge.ui.theme.doggoBlue
import com.example.androiddevchallenge.ui.theme.doggoBlueLight
import com.example.androiddevchallenge.ui.theme.doggoGreen
import com.example.androiddevchallenge.ui.theme.doggoGreenLight
import com.example.androiddevchallenge.ui.theme.doggoOrange
import com.example.androiddevchallenge.ui.theme.doggoOrangeLight
import com.example.androiddevchallenge.ui.theme.doggoRed
import com.example.androiddevchallenge.ui.theme.doggoRedLight

data class Dog(
    val name: String,
    val imageUrl: String,
    val description: String,
    val rating: Float,
    val hourlyRate: Int,
    val skills: List<Skill>
)

enum class Skill(val color: Color, val altColor: Color, val title: String) {
    Fetch(color = doggoRed, altColor = doggoRedLight, title = "Fetch"),
    Shake(color = doggoBlue, altColor = doggoBlueLight, title = "Shake"),
    Sit(color = doggoGreen, altColor = doggoGreenLight, title = "Sit"),
    Stay(color = doggoOrange, altColor = doggoOrangeLight, title = "Stay"),
}

sealed class UpdateSkill(val skill: Skill) {
    class Add(skill: Skill) : UpdateSkill(skill)
    class Remove(skill: Skill) : UpdateSkill(skill)
}

object DogRepository {
    val doggies = listOf(
        JEFF,
        BOB,
        SARAH,
        KATE
    )

    val featuredDog = SORA

    fun filteredDoggies(skills: List<Skill>): List<Dog> {
        return if (skills.isEmpty()) {
            doggies
        } else {
            doggies.filter { dog ->
                dog.skills.containsAll(skills)
            }
        }
    }
}

val SORA = Dog(
    name = "Sora",
    imageUrl = "https://i.pinimg.com/originals/ad/41/df/ad41df652b84c2dbd605f8a2351e754b.jpg",
    description = "Hi, I’m Sora! I know many tricks and recently became P.O.T.T.Y certified. Currently I am a " +
        "full-time house pup, but if you are interested in working with me, send a message and I will get back to you when I am taking new clients!",
    rating = 5F,
    hourlyRate = 50,
    skills = listOf(Fetch, Sit, Stay, Shake)
)

val JEFF = Dog(
    name = "Jeff",
    imageUrl = "https://images.unsplash.com/photo-1560115316-b1cb0591b631?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw" +
        "%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=2851&q=80",
    description = "The name is Jeff. I have worked with many hoomans testing many different products, though I specialize in all things Pork and Beef. I work tirelessly for my clients, and will make sure your products are tasty and production ready!",
    rating = 5F,
    hourlyRate = 30,
    skills = listOf(Stay, Shake)
)
val BOB = Dog(
    name = "Bob",
    imageUrl = "https://images.unsplash" +
        ".com/photo-1583512603805-3cc6b41f3edb?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=2000&q=80",
    description = "I have been a dogfooder for over 14 (dog) years. I am an expert in my field and you would be lucky to have me. Honestly even if you do request my services I may reject you. You will just have to roll the dice. Also my rate will likely continue to increase so I suggest you try to hire me now.",
    rating = 5F,
    hourlyRate = 30,
    skills = listOf(Fetch, Sit, Stay)
)
val SARAH = Dog(
    name = "Sarah",
    imageUrl = "https://images.unsplash.com/photo-1543466835-00a7907e9de1?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw" +
        "%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=2467&q=80",
    description = "Hi! I’m Sarah and I am a dogfooding hobbyist. I have helped my owners refine their treat selection and realized I have a knack for it. I have a lot of availability and am flexible on my rate, so if you do need some product dogfooding please reach out!",
    rating = 5F,
    hourlyRate = 30,
    skills = listOf(Sit, Stay)
)
val KATE = Dog(
    name = "Kate",
    imageUrl = "https://images.unsplash.com/photo-1553882809-a4f57e59501d?ixlib=rb-1.2" +
        ".1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1234&q=80",
    description = "Woof woof woof. Woof woof woof. Woof woof woof. Woof woof woof. Woof woof woof. Woof woof woof. Woof woof woof. Woof woof woof. Woof woof woof. Woof woof woof. Woof woof woof. Woof woof woof. Hire me, thanks.",
    rating = 5F,
    hourlyRate = 30,
    skills = listOf(Fetch, Shake)
)
