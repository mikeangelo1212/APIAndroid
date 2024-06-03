package com.example.examenu3.network

import com.example.examenu3.models.empleados.LoginRequest
import com.example.examenu3.models.empleados.LoginResponse
import com.example.examenu3.models.rickandmorty.RickAndMortyCharacter
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RickAndMortyAPI {
    @GET("/api/character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): RickAndMortyCharacter

}