package fes.aragon.usuarioslista.model

import androidx.room.Database
import androidx.room.RoomDatabase
import fes.aragon.usuarioslista.model.User
import fes.aragon.usuarioslista.model.UserDao

@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}