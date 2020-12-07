package com.yesferal.hornsapp.hada.dependency

/**
 * Factory is the basic class that extend of Dependency.
 *
 * In Hada, a Factory class represent a dependency that
 * will be created any time that is require.
 */
class Factory<T>(value: () -> Any): Dependency<T>(value) {
    override fun resolve(): Any {
        return value()
    }
}