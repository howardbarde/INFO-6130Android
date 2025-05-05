package com.example.project1

import com.google.gson.annotations.SerializedName

data class Rover(
    val name: String,
    @SerializedName("landing_date") val landingDate: String,
    @SerializedName("launch_date") val launchDate: String,
    val cameras: List<Camera>
)

data class Camera(
    val name: String,
    @SerializedName("full_name") val fullName: String
)

