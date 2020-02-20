package com.yesferal.hornsapp.hada.dependency

class Factory<T>(value: () -> Any): Dependency<T>(value) {
    override fun resolve(): Any {
        return value()
    }
}