package com.example.api

data class Cancion(
    val id: Int?,
    val Titulo: String,
    val Artista: String,
    val Albumid: Int
)

data class Canciones(
    val results: List<Canciones>
)

data class User(
    val idUsuario:Int?,
    val nombre:String,
    val pass:String,
    val nivel:Int
)



data class Message(val message: String)
data class Token(val token: String)
