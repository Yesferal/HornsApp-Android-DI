package com.yesferal.hornsapp.hada.dependency

class Singleton<T>(value: () -> Any): Dependency<T>(value) {
    private val instance by lazy { value() }
    override fun resolve(): Any {
        return instance
    }
}