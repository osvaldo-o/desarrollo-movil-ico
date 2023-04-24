package fes.aragon.appexamenhttp.model

import fes.aragon.appexamenhttp.model.local.UserEntity

data class User(val id: Int, val name: String, val last: String, val email: String, val image: String)

fun User.toUserEntity() : UserEntity = UserEntity(this.id,this.name,this.last,this.email,this.image)