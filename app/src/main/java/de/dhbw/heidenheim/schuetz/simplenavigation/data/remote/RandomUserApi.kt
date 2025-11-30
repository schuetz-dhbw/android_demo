package de.dhbw.heidenheim.schuetz.simplenavigation.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {
    @GET("api/")
    suspend fun getRandomUsers(
        @Query("results") results: Int = 1
    ): RandomUsersDto
}