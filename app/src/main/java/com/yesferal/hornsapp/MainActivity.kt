package com.yesferal.hornsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val container by lazy {
        (application as MyApp).container
    }

    private val actionListener by lazy {
        container.resolve<MainContract.ActionListener>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionListener.bind(this)
        actionListener.onCreate()
    }

    override fun show(
        title: String,
        description: String
    ) {
        textViewTitle.text = title
        textViewDescription.text = description
    }
}
