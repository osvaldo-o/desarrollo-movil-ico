package fes.aragon.oauth.model.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(UserEntity::class), version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}