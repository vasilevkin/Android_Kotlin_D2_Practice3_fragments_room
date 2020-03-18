package com.vasilevkin.fragmentsroom.base


interface IView {
    fun showLoading()
    fun hideLoading()
    fun showError(msg: String)
}