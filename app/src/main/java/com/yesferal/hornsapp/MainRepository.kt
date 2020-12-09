package com.yesferal.hornsapp

class MainRepository(
    private val message: String,
    private val description: String
) {
    fun getMessage() = message
    fun getDescription() = description
}