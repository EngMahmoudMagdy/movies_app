package com.magdy.moviesapp.ui.main.viewModels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class CustomViewModel : ViewModel() {
    val backPressedLiveData = MutableLiveData<Void?>()
    val showToastLiveData = MutableLiveData<Int>()
    val showToastStringLiveData = MutableLiveData<String>()
    val showLoadingLiveData = MutableLiveData<Boolean>()
    val openLoginLiveData = MutableLiveData<Boolean>()

    fun setOnBack(v: View){
        onBack()
    }
    val onBack = {
        backPressedLiveData.value = null
    }

    fun showLoading() {
        showLoadingLiveData.value = true
    }

    fun dismissLoading() {
        showLoadingLiveData.value = false
    }

    fun showToast(resId: Int) {
        showToastLiveData.value = resId
    }

    fun showToast(s: String) {
        showToastStringLiveData.value = s
    }
}