package com.omerilhanli.martichallenge.ui.base

interface BaseNavigator {
    fun handleError(error: Throwable)
    fun onFragmentDetached(tag: String)
}