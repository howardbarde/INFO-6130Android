package com.example.project1

data class RoverParent(
    val rover: Rover,
    val isExpanded: Boolean = false,
    val cameras: List<String> = listOf()
)

