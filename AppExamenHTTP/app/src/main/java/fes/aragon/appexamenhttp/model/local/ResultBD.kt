package fes.aragon.appexamenhttp.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import fes.aragon.appexamenhttp.model.Name
import fes.aragon.appexamenhttp.model.Picture

@Entity(tableName = "User")
data class ResultBD(@PrimaryKey(autoGenerate = true) val id: Int = 0, val email: String = "", val name: Name = Name(), val picture: Picture = Picture(""))