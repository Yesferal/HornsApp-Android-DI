package com.yesferal.hornsapp.hada.dependency

import com.yesferal.hornsapp.hada.parameter.Parameters

/**
 * Factory is the basic class that extend of Dependency.
 *
 * In Hada, a Factory class represent a dependency that
 * will be created any time that is require.
 */
class Factory<T>(
    tag: String = "",
    value: (Parameters) -> T
): Dependency<T>(tag, value) {
    override fun resolve(params: Parameters): T {
        return value(params)
    }
}