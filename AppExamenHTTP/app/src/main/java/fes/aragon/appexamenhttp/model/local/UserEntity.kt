package fes.aragon.appexamenhttp.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import fes.aragon.appexamenhttp.model.User

@Entity(tableName = "User")
data class UserEntity(@PrimaryKey(autoGenerate = true) val id: Int, val name: String, val last: String, val email: String, val image: String)

fun UserEntity.toUser() : User = User(this.id,this.name,this.last,this.email,this.image)