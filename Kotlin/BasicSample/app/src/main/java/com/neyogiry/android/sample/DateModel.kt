package com.neyogiry.android.sample

import android.os.Handler

class DateModel {

    var mDates: ArrayList<String> = ArrayList()

    fun saveDate(date: String, callback: DateInterfaces.Model) {
        try {
            val handler = Handler()
            handler.postDelayed({
                mDates.add(0, date)
                onSuccess(callback)
            }, 5000)
        } catch (e: Exception) {
            onError(callback, e.toString())
        }

    }

    private fun onSuccess(callback: DateInterfaces.Model) {
        callback.onResponse(mDates)
    }

    private fun onError(callback: DateInterfaces.Model, error: String) {
        callback.onError(error)
    }

}