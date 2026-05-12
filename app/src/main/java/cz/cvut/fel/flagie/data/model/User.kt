package cz.cvut.fel.flagie.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val age: Int
)