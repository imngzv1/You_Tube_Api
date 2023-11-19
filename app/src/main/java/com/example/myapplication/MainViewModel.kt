package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var counter = 0

    private val _counterLv = MutableLiveData<Int>()
    val counterLv = _counterLv as LiveData<Int>

    fun dec() {
        counter -= 1
        _counterLv.value = counter
    }

    fun inc() {
        counter += 1
        _counterLv.value = counter
    }
}