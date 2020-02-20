package com.yesferal.hornsapp

interface MainContract {
    interface ActionListener {
        fun bind(view: View)
        fun onCreate()
    }

    interface View {
        fun show(message: String)
    }
}