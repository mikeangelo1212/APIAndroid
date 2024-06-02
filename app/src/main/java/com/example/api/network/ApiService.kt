package com.example.api.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path

data class Videojuego(
    val id: String?,
    val nombre: String,
    val formato: String,
    val plataforma: String,
    val costo: Double
)

interface ApiService {
    @GET("misvideojuegos")
    fun getVideojuegos(): Call<List<Videojuego>>

    @GET("misvideojuegos/nombre/{nombre}")
    fun getVideojuegoPorNombre(@Path("nombre") nombre: String): Call<List<Videojuego>>

    @POST("misvideojuegos")
    fun addVideojuego(@Body videojuego: Videojuego): Call<Void>

    @DELETE("misvideojuegos/nombre/{nombre}")
    fun deleteVideojuego(@Path("nombre") nombre: String): Call<Void>

    @PUT("misvideojuegos/nombre/{nombre}")
    fun updateVideojuego(@Path("nombre") nombre: String, @Body videojuego: Videojuego): Call<Void>
}