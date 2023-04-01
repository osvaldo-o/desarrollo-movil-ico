package fes.aragon.usuarioslista

import androidx.room.*

@Dao
interface UserDao {

    @Query(value = "SELECT * FROM user")
    fun getAllUser() : MutableList<User>

    @Insert
    fun addUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

}