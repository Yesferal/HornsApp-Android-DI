package com.yesferal.hornsapp.hadi_android

import android.app.Activity
import androidx.fragment.app.Fragment
import com.yesferal.hornsapp.hadi.container.Container

fun Fragment.hadi(): Container {
    return (requireActivity().application as HadiApp).container
}

fun Activity.hadi(): Container {
    return (application as HadiApp).container
}
