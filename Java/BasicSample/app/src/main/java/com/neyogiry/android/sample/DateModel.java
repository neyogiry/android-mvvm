package com.neyogiry.android.sample;

import android.os.Handler;

import java.util.ArrayList;

public class DateModel {

    ArrayList<String> mDates = new ArrayList<>();

    public DateModel() {
    }

    public void saveDate(final String date, final DateInterfaces.Model callback) {
        try{
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDates.add(0, date);
                    onSuccess(callback);
                }
            }, 5000);
        }catch (Exception e){
            onError(callback, e.toString());
        }
    }

    public void onSuccess(DateInterfaces.Model callback) {
        callback.onResponse(mDates);
    }

    public void onError(DateInterfaces.Model callback, String error) {
        callback.onError(error);
    }

}
