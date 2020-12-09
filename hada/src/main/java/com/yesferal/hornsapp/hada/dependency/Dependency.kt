package com.yesferal.hornsapp.hada.dependency

/**
 * Dependency is the class that we necessary use in any Container implementation.
 *
 * You could extend this class with any class,
 * and do this new dependency work it as you need to.
 */
abstract class Dependency<T>(
    val tag: String,
    val value: () -> Any
) {
    abstract fun resolve(): Any
}