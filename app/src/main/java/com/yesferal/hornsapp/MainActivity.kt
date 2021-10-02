package com.yesferal.hornsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.yesferal.hornsapp.hadi_android.hadi

class MainActivity : AppCompatActivity(), MainContract.View {

    private val actionListener by lazy {
        hadi().resolve<MainContract.ActionListener>()
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
        findViewById<TextView>(R.id.textViewTitle).text = title
        findViewById<TextView>(R.id.textViewDescription).text = description
    }
}
