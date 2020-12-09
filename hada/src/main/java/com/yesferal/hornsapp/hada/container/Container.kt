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
    abstract fun <T> _register(className: String, dependency: Dependency<T>)
    abstract fun _resolve(className: String): Any

    inline infix fun <reified T> register(
        dependency: Dependency<T>
    ) = _register("${T::class.java}.${dependency.tag}", dependency)

    inline fun <reified T> resolve(tag: String = ""): T = _resolve("${T::class.java}.${tag}") as T
}
