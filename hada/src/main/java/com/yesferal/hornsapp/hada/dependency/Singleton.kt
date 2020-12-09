package com.yesferal.hornsapp.hada.dependency

/**
 * Singleton is the basic class that extend of Dependency.
 *
 * In Hada, a Singleton class represent a dependency that
 * will be created only one time, just the first time that is require.
 */
class Singleton<T>(
    tag: String = "",
    value: () -> Any
): Dependency<T>(tag, value) {
    private val instance by lazy { value() }
    override fun resolve(): Any {
        return instance
    }
}