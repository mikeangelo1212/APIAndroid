package com.example.examenu3.network

import com.example.examenu3.Cancion
import com.example.examenu3.Message
import com.example.examenu3.Token
import com.example.examenu3.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CancionesAPI {
    @GET("usuarios/{nombre}")
    suspend fun getUsuarioPorNombre(@Path("nombre") nombre: String): User

    @GET("canciones")
    suspend fun getCanciones(@Header("Authorization") bearerToken:String): List<Cancion>

    //atributos que o se usen se mandan nulos desde afuera
    @POST("register")
    suspend fun register(@Body user: User)

    @POST("login")
    suspend fun login(@Body user: User): Token//no se si usamos el token para algo pero si es que si, hay
    //que crear una clase para el JSON
    @POST("compare")
    suspend fun compare(@Body user: User): Message

    @POST("cancion")
    suspend fun addCancion(@Header("Authorization") bearerToken:String, @Body cancion: Cancion)//aqui mandaremo el id nulo

    @GET("cancion/{id}")
    suspend fun getCancion(@Header("Authorization") bearerToken:String,@Path("id") id: String):List<Cancion>//aqui mandaremo el id nulo


    @PUT("cancion/{id}")
    suspend fun modificarCancion(@Header("Authorization") bearerToken:String,@Path("id") id: String, @Body cancion: Cancion)//aqui mandaremo el id nulo

    //porque ya va en el header
    @DELETE("cancion/{id}")
    suspend fun eliminarCancion(@Header("Authorization") bearerToken:String,@Path("id") id: String)
}