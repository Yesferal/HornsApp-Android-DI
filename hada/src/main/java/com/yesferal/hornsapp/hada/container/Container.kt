package com.yesferal.hornsapp.hada.container

import com.yesferal.hornsapp.hada.dependency.Dependency

/**
 * The Container is the main abstract class.
 *
 * You could implement this abstract class with any other class,
 * in this case we use Hada class to implement it,
 * but you can create your own and override it as you need.
 */
abstract class Container {
    abstract fun <T> register(clazz: Class<T>, dependency: Dependency<T>)
    abstract fun <T> resolve(clazz: Class<T>): Any

    inline infix fun <reified T> register(
        dependency: Dependency<T>
    ) = register(T::class.java, dependency)

    inline fun <reified T> resolve(): T = resolve(T::class.java) as T
}
