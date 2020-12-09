package com.yesferal.hornsapp.hada.container

import com.yesferal.hornsapp.hada.dependency.Factory
import com.yesferal.hornsapp.hada.dependency.Singleton
import org.junit.Assert.assertNotSame
import org.junit.Assert.assertSame
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import java.util.*

class HadaTest {

    @Test
    fun resolve_any_dependency_ReturnTrue() {
        val date  = Date()

        val hada = Hada()
        hada register Singleton<Date>{
            date
        }

        assertSame(hada.resolve<Date>(), date)
    }

    @Test
    fun resolve_singleton_dependency_ReturnTrue() {
        val hada = Hada()
        hada register Singleton<Date>{
            Date()
        }

        assertSame(hada.resolve<Date>(), hada.resolve<Date>())
    }

    @Test
    fun resolve_factory_dependency_ReturnTrue() {
        val hada = Hada()
        hada register Factory<Date>{
            Date()
        }

        assertNotSame(hada.resolve<Date>(), hada.resolve<Date>())
    }

    @get:Rule
    val exception: ExpectedException = ExpectedException.none()

    @Test
    fun register_dependency_Throw_registered_twice_exception() {
        val hada = Hada()
        hada register Factory<Date>{
            Date()
        }

        exception.expect(DependencyRegisteredTwiceException::class.java)
        hada register Factory<Date>{
            Date()
        }
    }

    @Test
    fun resolve_dependency_Throw_not_found_exception() {
        val hada = Hada()

        exception.expect(DependencyNotFoundException::class.java)
        hada.resolve<Date>()
    }
}