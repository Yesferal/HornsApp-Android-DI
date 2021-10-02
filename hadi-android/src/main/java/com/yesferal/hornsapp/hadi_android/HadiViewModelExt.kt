package com.yesferal.hornsapp.hadi_android

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yesferal.hornsapp.hadi.parameter.Parameters

inline fun <reified T : ViewModel, reified F : ViewModelProvider.Factory> Fragment.getViewModel(
    param: Any? = null
) = ViewModelProvider(
    viewModelStore,
    resolveFactory<F>(param)
).get(T::class.java)

inline fun <reified F : ViewModelProvider.Factory> Fragment.resolveFactory(param: Any?): F {
    return param?.let {
        hadi().resolve(params = Parameters(it))
    } ?: run {
        hadi().resolve()
    }
}
