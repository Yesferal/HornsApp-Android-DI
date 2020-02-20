package com.yesferal.hornsapp.hada.dependency

abstract class Dependency<T>(val value: () -> Any) {
    abstract fun resolve(): Any
}