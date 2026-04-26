package cz.cvut.fel.flagie.data.db

import cz.cvut.fel.flagie.data.model.User

// Převod z databáze do aplikace
fun UserEntity.toDomain() = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    age = age
)

// Převod z aplikace do databáze
fun User.toEntity() = UserEntity(
    id = id,
    firstName = firstName,
    lastName = lastName,
    age = age
)