package fes.aragon.oauth.model.local

import androidx.room.*

@Dao
interface UserDao {
    @Query(value = "SELECT * FROM user")
    suspend fun getAllUser() : MutableList<UserEntity>

    @Insert()
    suspend fun addUser(user: UserEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)
}