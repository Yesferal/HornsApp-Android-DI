package com.yesferal.hornsapp.hada.parameter

import com.yesferal.hornsapp.hada.exception.ParameterNotFoundException

class Parameters (
    val firstParam: Any?,
    val secondParam: Any? = null
) {
    inline operator fun <reified T> component1(): T {
        firstParam?.let {
            return it as T
        }?: throw ParameterNotFoundException(0, T::class.java.toString())
    }

    inline operator fun <reified T> component2(): T {
        secondParam?.let {
            return it as T
        }?: throw ParameterNotFoundException(1, T::class.java.toString())
    }
}