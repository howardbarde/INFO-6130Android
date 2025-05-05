package com.example.project1

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.awaitResponse

class RoverRepository {
    private val api: NasaApiService = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NasaApiService::class.java)

    suspend fun getRovers(): List<Rover>? {
        return try {
            val response = api.getRovers().awaitResponse()
            if (response.isSuccessful) {
                response.body()?.rovers
            } else {
                Log.e("RoverRepository", "API call failed: ${response.errorBody()?.string()}")
                null
            }
        } catch (e: Exception) {
            Log.e("RoverRepository", "Exception: ${e.message}")
            null
        }
    }
}
