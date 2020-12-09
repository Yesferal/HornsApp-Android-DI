package com.yesferal.hornsapp.hada.dependency

/**
 * Factory is the basic class that extend of Dependency.
 *
 * In Hada, a Factory class represent a dependency that
 * will be created any time that is require.
 */
class Factory<T>(
    tag: String = "",
    value: () -> Any
): Dependency<T>(tag, value) {
    override fun resolve(): Any {
        return value()
    }
}