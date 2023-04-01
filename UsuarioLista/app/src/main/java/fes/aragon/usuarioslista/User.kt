package fes.aragon.usuarioslista

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(@PrimaryKey(autoGenerate = true) val id: Int = 0, val name: String, val url: String)