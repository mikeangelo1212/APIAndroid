package com.example.examenu3.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitObject {
    private const val URL_RANDOM_USER = "https://randomuser.me"
    private const val URL_RICK_AND_MORTY = "https://rickandmortyapi.com"
    private const val URL_EMPLEADOS = "https://sonicapi-production.up.railway.app"
    private const val URL_CANCIONES = "https://apiandroid.up.railway.app/"

//    val randomAPI = Retrofit.Builder()
//        .baseUrl(URL_RANDOM_USER)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//        .create(RandomAPI::class.java)
//
//    val rickAndMortyAPI = Retrofit.Builder()
//        .baseUrl(URL_RICK_AND_MORTY)
//        .build()
//        .create(RickAndMortyAPI::class.java)
//
//    val empleadosApi = Retrofit.Builder()
//        .baseUrl(URL_EMPLEADOS)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//        .create(EmpleadosAPI::class.java)

    val cancionesApi = Retrofit.Builder()
        .baseUrl(URL_CANCIONES)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CancionesAPI::class.java)

}