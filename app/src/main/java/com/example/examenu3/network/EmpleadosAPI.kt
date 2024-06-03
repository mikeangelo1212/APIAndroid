package com.example.examenu3.network

//import com.example.examenu3.Empleado
import com.example.examenu3.models.Test
import com.example.examenu3.models.empleados.LoginRequest
import com.example.examenu3.models.empleados.LoginResponse
import com.example.examenu3.models.rickandmorty.RickAndMortyCharacter
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

//interface EmpleadosAPI {
//    @POST("/empleados")
//    suspend fun insertEmployee(@Header("Authorization") bearerToken: String, @Body employee: Empleado)
//
//    @GET("/empleados")
//    suspend fun getEmployees() : List<Empleado>
//
//    @GET("/empleados")
//    suspend fun getEmployee(@Query("id") idEmpleado: String) : List<Empleado>
//
//    @PUT("/empleados/{idEmpleado}")
//    suspend fun updateEmployee(@Header("Authorization") bearerToken: String, @Path("idEmpleado") idEmpleado: String, @Body empleado: Empleado)
//
//    @DELETE("/empleados/{idEmpleado}")
//    suspend fun deleteEmployee(@Header("Authorization") bearerToken: String, @Path("idEmpleado") idEmpleado: String)
//
//    @POST("/login")
//    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
//
//    @GET(".")
//    suspend fun sonic(): Test
////    @GET("/login")
////    fun sign(@Header("Bearer") bearerToken: String)
//}