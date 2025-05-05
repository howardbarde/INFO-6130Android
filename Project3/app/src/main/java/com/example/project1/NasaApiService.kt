package com.example.project1

import retrofit2.http.GET
import retrofit2.Call

interface NasaApiService {
    @GET("mars-photos/api/v1/rovers?api_key=DEMO_KEY")
    fun getRovers(): Call<RoverResponse>
}

data class RoverResponse(val rovers: List<Rover>)
