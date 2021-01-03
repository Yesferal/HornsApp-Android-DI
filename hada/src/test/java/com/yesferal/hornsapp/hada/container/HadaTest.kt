package com.yesferal.hornsapp.hada.container

import com.yesferal.hornsapp.hada.dependency.Factory
import com.yesferal.hornsapp.hada.dependency.Singleton
import com.yesferal.hornsapp.hada.exception.DependencyNotFoundException
import com.yesferal.hornsapp.hada.exception.DependencyRegisteredTwiceException
import com.yesferal.hornsapp.hada.exception.ParameterNotFoundException
import com.yesferal.hornsapp.hada.parameter.Parameters
import org.junit.Assert.*
import org.junit.Test

class HadaTest {
    class Note(
        val title: String,
        val description: String
    )

    private val title = "Title"
    private val description = "Description"

    @Test
    fun resolve_any_dependency_ReturnTrue() {
        val noteExpected = Note(title, description)

        val hada = Hada()
        hada register Singleton {
            noteExpected
        }

        assertSame(noteExpected, hada.resolve<Note>())
    }

    @Test
    fun resolve_any_dependency_with_tag_ReturnTrue() {
        val tag = "tagExample"
        val noteExpected = Note(title, description)

        val hada = Hada()
        hada register Singleton(tag = tag) {
            noteExpected
        }

        assertSame(noteExpected, hada.resolve<Note>(tag = tag))
    }

    @Test
    fun resolve_singleton_dependency_ReturnTrue() {
        val hada = Hada()
        hada register Singleton {
            Note(title, description)
        }

        assertSame(hada.resolve<Note>(), hada.resolve<Note>())
    }

    @Test
    fun resolve_factory_dependency_ReturnTrue() {
        val hada = Hada()
        hada register Factory {
            Note(title, description)
        }

        assertNotSame(hada.resolve<Note>(), hada.resolve<Note>())
        assertSame(hada.resolve<Note>().title, hada.resolve<Note>().title)
        assertSame(hada.resolve<Note>().description, hada.resolve<Note>().description)
    }

    @Test
    fun register_two_dependency_with_tag_Return_true() {
        val hada = Hada()
        val tag = "tagExample"

        hada register Singleton {
            Note(title, description)
        }

        hada register Singleton(tag = tag) {
            Note(title, description)
        }

        assertNotSame(hada.resolve<Note>(), hada.resolve<Note>(tag = tag))
        assertSame(hada.resolve<Note>(), hada.resolve<Note>())
        assertSame(hada.resolve<Note>(tag = tag), hada.resolve<Note>(tag = tag))
    }

    @Test
    fun register_two_dependency_without_tag_Throw_dependency_registered_twice_exception() {
        val hada = Hada()
        hada register Factory {
            Note(title, description)
        }

        assertThrows(DependencyRegisteredTwiceException::class.java) {
            hada register Factory {
                Note(title, description)
            }
        }
    }

    @Test
    fun resolve_dependency_Throw_dependency_not_found_exception() {
        val hada = Hada()

        assertThrows(DependencyNotFoundException::class.java) {
            hada.resolve<Note>()
        }
    }

    @Test
    fun resolve_singleton_dependency_with_parameters_Return_true() {
        val hada = Hada()
        hada register Singleton { (titleParam: String, descriptionParam: String) ->
            Note(title = titleParam, description = descriptionParam)
        }

        assertSame(hada.resolve<Note>(params = Parameters(title, description)), hada.resolve<Note>(params = Parameters(title, description)))
        assertSame(title, hada.resolve<Note>(params = Parameters(title, description)).title)
        assertSame(description, hada.resolve<Note>(params = Parameters(title, description)).description)
    }

    @Test
    fun resolve_factory_dependency_with_parameters_Return_true() {
        val hada = Hada()
        hada register Factory { (titleParam: String, descriptionParam: String) ->
            Note(title = titleParam, description = descriptionParam)
        }

        assertSame(title, hada.resolve<Note>(params = Parameters(title, description)).title)
        assertSame(description, hada.resolve<Note>(params = Parameters(title, description)).description)
    }

    @Test
    fun resolve_factory_dependency_with_parameters_Throw_parameter_not_found_exception() {
        val hada = Hada()
        hada register Factory { (titleParam: String, descriptionParam: String) ->
            Note(title = titleParam, description = descriptionParam)
        }

        assertThrows(ParameterNotFoundException::class.java) {
            hada.resolve<Note>(params = Parameters(title))
        }
    }
}