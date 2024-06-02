package com.example.pet.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Detail(
    val id:Int
)