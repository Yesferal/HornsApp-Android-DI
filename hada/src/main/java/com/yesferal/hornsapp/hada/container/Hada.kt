package com.yesferal.hornsapp.hada.container

import com.yesferal.hornsapp.hada.dependency.Dependency
import java.lang.Exception

/**
 * Hada is the basic implementation of Container.
 *
 * In Hada, you can register any class that implement Dependency.
 * In that moment you can register Singleton and Factory dependencies
 * in any class that implement the Container interface.
 */
class Hada: Container() {
    private val dependencies: HashMap<Class<*>, Dependency<*>> = hashMapOf()

    override fun <T> register(clazz: Class<T>, dependency: Dependency<T>) {
        dependencies[clazz] = dependency
    }

    override fun <T> resolve(clazz: Class<T>): Any {
        return dependencies[clazz]?.resolve()
            ?: throw Exception("${this::class.java} could not resolve instance of [${clazz}]")
    }
}