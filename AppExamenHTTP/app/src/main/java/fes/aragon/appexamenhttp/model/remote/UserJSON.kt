package fes.aragon.appexamenhttp.model

data class UserJSON(val results: List<Result>)
data class Result(val email: String, val name: Name, val picture: Picture)
data class Picture(val large: String)
data class Name(val first: String, val last: String, val title: String)


fun Result.toUser() : User = User(0,"${this.name.title} ${this.name.first}",this.name.last,this.email,this.picture.large)