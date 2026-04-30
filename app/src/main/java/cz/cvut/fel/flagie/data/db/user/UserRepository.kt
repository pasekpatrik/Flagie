package cz.cvut.fel.flagie.data.db.user

import cz.cvut.fel.flagie.data.db.user.mapper.toDomain
import cz.cvut.fel.flagie.data.db.user.mapper.toEntity
import cz.cvut.fel.flagie.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(private val userDao: UserDao) {

    val allUsers: Flow<List<User>> = userDao.getAllUsers().map { entities ->
        entities.map { it.toDomain() }
    }

    suspend fun addUser(user: User) {
        userDao.insertUser(user.toEntity())
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user.toEntity())
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user.toEntity())
    }

    suspend fun clearAll() {
        userDao.deleteAllUsers()
    }
}