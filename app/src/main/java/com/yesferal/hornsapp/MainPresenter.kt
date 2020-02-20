package com.yesferal.hornsapp

class MainPresenter(private val mainRepository: MainRepository): MainContract.ActionListener {
    private var mView: MainContract.View? = null

    override fun bind(view: MainContract.View) {
        mView = view
    }

    override fun onCreate() {
        mView?.show(message = mainRepository.getMessage())
    }
}