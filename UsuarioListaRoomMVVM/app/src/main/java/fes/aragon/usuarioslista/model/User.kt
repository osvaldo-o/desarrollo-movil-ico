package fes.aragon.usuarioslista.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(@PrimaryKey(autoGenerate = true) val id: Int = 0, val name: String, val email: String, val age: Int, val sex: String)