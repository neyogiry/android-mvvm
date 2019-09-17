package com.neyogiry.android.sample

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import java.util.*


class DateViewModel: ViewModel(), DateInterfaces.Model {

    private var isLoading: MutableLiveData<Boolean>
    private var list: MutableLiveData<List<String>>
    private var message: MutableLiveData<String>
    private var mModel: DateModel

    init {
        isLoading = MutableLiveData()
        list = MutableLiveData()
        message = MutableLiveData()
        mModel = DateModel()
    }

    fun getLoadingStatus(): MutableLiveData<Boolean> {
        return isLoading
    }

    fun setLoadingStatus(isLoading: Boolean) {
        this.isLoading.postValue(isLoading)
    }

    fun getDateList(): MutableLiveData<List<String>> {
        return list
    }

    fun setDateList(list: List<String>) {
        this.list.postValue(list)
    }

    fun getMessage(): MutableLiveData<String> {
        return message
    }

    fun setMessage(message: String) {
        this.message.postValue(message)
    }

    fun addDate() {
        setLoadingStatus(true)
        mModel.saveDate(getTimeCurrent(), this)
    }

    private fun getTimeCurrent(): String {
        val timeCurrent: String
        val calendar = Calendar.getInstance()
        timeCurrent = calendar.time.toString()
        return timeCurrent
    }

    override fun onResponse(any: Any) {
        setLoadingStatus(false)
        setDateList(any as List<String>)
    }

    override fun onError(error: String) {
        setLoadingStatus(false)
        setMessage(error)
    }


}