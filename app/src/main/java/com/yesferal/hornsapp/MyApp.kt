package com.yesferal.hornsapp

import android.app.Application
import com.yesferal.hornsapp.hada.container.Container
import com.yesferal.hornsapp.hada.container.Hada
import com.yesferal.hornsapp.hada.dependency.Factory
import com.yesferal.hornsapp.hada.dependency.Singleton
import com.yesferal.hornsapp.hada.parameter.Parameters

class MyApp: Application() {
    /**
     * Initialize the Container for the app
     * in the Application
     * so you can use it in any Activity
     */
    val container: Container = Hada()
    override fun onCreate() {
        super.onCreate()

        initBaseModule()
    }

    /**
     * Here we initialize the dependencies for the entire app
     * in the Base Module
     */
    private fun initBaseModule() {
        container register Factory(tag = "Title") { "Title: Hada Container" }

        container register Factory { (string: String) ->
            string
        }

        container register Singleton {
            MainRepository(
                message = container.resolve(tag = "Title"),
                description = container.resolve(params = Parameters("Description: Inserting value as Parameter"))
            )
        }

        container register Factory<MainContract.ActionListener> {
            MainPresenter(mainRepository = container.resolve())
        }
    }
}