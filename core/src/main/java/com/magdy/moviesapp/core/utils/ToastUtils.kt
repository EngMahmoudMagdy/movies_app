package com.magdy.moviesapp.core.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

object ToastUtils {


    var toast: Toast? = null
    fun Fragment.showToast(resId: Int) {
        context?.run { showToast(resId) }
    }

    fun Fragment.showToast(value: String) {
        context?.run { showToast(value) }
    }

    fun Context.showToast(resId: Int) {
        toast?.cancel()
        toast = Toast.makeText(this, resId, Toast.LENGTH_SHORT).also { it.show() }
    }

    fun Context.showToast(value: String) {
        toast?.cancel()
        toast = Toast.makeText(this, value, Toast.LENGTH_SHORT).also { it.show() }
    }

    fun Fragment.showToastLong(resId: Int) {
        context?.run { showToastLong(resId) }
    }

    fun Context.showToastLong(resId: Int) {
        toast?.cancel()
        toast = Toast.makeText(this, resId, Toast.LENGTH_LONG).also { it.show() }
    }
}