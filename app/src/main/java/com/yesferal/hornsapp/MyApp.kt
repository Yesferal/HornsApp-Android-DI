package com.yesferal.hornsapp

import android.app.Application
import com.yesferal.hornsapp.hadi.container.Container
import com.yesferal.hornsapp.hadi.container.Hadi
import com.yesferal.hornsapp.hadi.dependency.Factory
import com.yesferal.hornsapp.hadi.dependency.Singleton
import com.yesferal.hornsapp.hadi.parameter.Parameters
import com.yesferal.hornsapp.hadi_android.HadiApp

class MyApp: Application(), HadiApp {
    /**
     * Initialize the Container for the app
     * in the Application
     * so you can use it in any Activity
     */
    override val container: Container = Hadi()

    override fun onCreate() {
        super.onCreate()

        initBaseModule()
    }

    /**
     * Here we initialize the dependencies for the entire app
     * in the Base Module
     */
    private fun initBaseModule() {
        container register Factory(tag = "Title") { "Title: Hadi Container" }

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