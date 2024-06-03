package com.example.examenu3.network

import com.example.examenu3.models.randomuser.RandomUser
import retrofit2.http.GET

interface RandomAPI {
    @GET("/api")
    suspend fun getRandomUser(): RandomUser
}