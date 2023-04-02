package fes.aragon.usuarioslista.model

import androidx.room.*
import fes.aragon.usuarioslista.model.User

@Dao
interface UserDao {
    @Query(value = "SELECT * FROM user")
    fun getAllUser() : MutableList<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)
}