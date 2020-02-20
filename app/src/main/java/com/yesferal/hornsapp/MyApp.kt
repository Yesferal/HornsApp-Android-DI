package com.yesferal.hornsapp

import android.app.Application
import com.yesferal.hornsapp.hada.container.Container
import com.yesferal.hornsapp.hada.container.Hada
import com.yesferal.hornsapp.hada.container.register
import com.yesferal.hornsapp.hada.container.resolve
import com.yesferal.hornsapp.hada.dependency.Factory
import com.yesferal.hornsapp.hada.dependency.Singleton

class MyApp: Application() {
    val container: Container = Hada()

    override fun onCreate() {
        super.onCreate()

        /**
         * You could use any of this two options
         */
        firstOptionToInitMainModule()
    }

    private fun firstOptionToInitMainModule() {
        container register Factory<String> { "Hada Container: First Option !" }
        container register Singleton<MainRepository> {
            MainRepository(container.resolve())
        }
        container register Factory<MainContract.ActionListener> {
            MainPresenter(container.resolve())
        }
    }

    private fun secondOptionToInitMainModule() {
        container.register(String::class.java, Singleton {
            "Hada Container: Second Option !"
        })
        container.register(MainRepository::class.java, Singleton {
            MainRepository(container.resolve())
        })
        container.register(MainContract.ActionListener::class.java, Factory {
            MainPresenter(container.resolve())
        })
    }
}