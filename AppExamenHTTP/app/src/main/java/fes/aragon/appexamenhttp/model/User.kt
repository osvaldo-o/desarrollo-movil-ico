package fes.aragon.appexamenhttp.model

data class User(val results: List<Result>)

data class Result(val email: String, val name: Name, val picture: Picture)

data class Picture(val large: String)

data class Name(val first: String, val last: String, val title: String)