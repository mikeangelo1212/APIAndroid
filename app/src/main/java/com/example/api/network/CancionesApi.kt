package com.example.api.network

import com.example.api.Cancion
import com.example.api.Message
import com.example.api.Token
import com.example.api.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CancionesApi {

    @GET("usuarios/{nombre}")
    suspend fun getUsuarioPorNombre(@Path("nombre") nombre: String): User

    @GET("canciones")
    suspend fun getCanciones(): List<Cancion>

    //atributos que o se usen se mandan nulos desde afuera
    @POST("register")
    suspend fun register(@Body user: User):Call<Void>

    @POST("login")
    suspend fun login(@Body user: User): Token//no se si usamos el token para algo pero si es que si, hay
    //que crear una clase para el JSON
    @POST("compare")
    suspend fun compare(@Body user: User): Message

    @PUT("cancion/{id}")
    suspend fun modificarCancion(@Path("id") id: Int, @Body cancion: Cancion):Call<Void>//aqui mandaremo el id nulo
    //porque ya va en el header
    @DELETE("cancion/{id}")
    suspend fun eliminarCancion():Call<Void>
}
