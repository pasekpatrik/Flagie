package cz.cvut.fel.flagie.data.db.user.mapper

import cz.cvut.fel.flagie.data.db.user.UserEntity
import cz.cvut.fel.flagie.data.model.User

fun UserEntity.toDomain() = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    age = age
)

fun User.toEntity() = UserEntity(
    id = id,
    firstName = firstName,
    lastName = lastName,
    age = age
)