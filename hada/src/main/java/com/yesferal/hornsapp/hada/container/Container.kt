package com.yesferal.hornsapp.hada.container

import com.yesferal.hornsapp.hada.dependency.Dependency

interface Container {
    fun <T> register(clazz: Class<T>, dependency: Dependency<T>)
    fun <T> resolve(clazz: Class<T>): Any
}

inline infix fun <reified T> Container.register(
    dependency: Dependency<T>
) = register(T::class.java, dependency)

inline fun <reified T> Container.resolve(): T = resolve(T::class.java) as T