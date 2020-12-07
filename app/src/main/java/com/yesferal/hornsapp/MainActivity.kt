package com.yesferal.hornsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val actionListener by lazy {
        getContainer().resolve<MainContract.ActionListener>()
    }

    private fun getContainer() = (application as MyApp).container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionListener.bind(this)
        actionListener.onCreate()
    }

    override fun show(message: String) {
        textViewMessage.text = message
    }
}
