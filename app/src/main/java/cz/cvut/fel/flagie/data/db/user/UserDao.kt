package cz.cvut.fel.flagie.data.db.user

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("DELETE FROM User")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM User WHERE id = :id")
    suspend fun getUserById(id: Long): UserEntity?

    @Query("SELECT * FROM User ORDER BY lastName ASC")
    fun getAllUsers(): Flow<List<UserEntity>>
}