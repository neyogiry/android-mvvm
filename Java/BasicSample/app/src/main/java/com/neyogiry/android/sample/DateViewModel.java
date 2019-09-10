package com.neyogiry.android.sample;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Calendar;
import java.util.List;

public class DateViewModel extends ViewModel implements DateInterfaces.Model {

    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<List<String>> list;
    private MutableLiveData<String> message;
    private DateModel mModel;

    public DateViewModel() {
        this.isLoading = new MutableLiveData<>();
        this.list = new MutableLiveData<>();
        this.message = new MutableLiveData<>();
        this.mModel = new DateModel();
    }

    MutableLiveData<Boolean> getLoadingStatus(){
        return isLoading;
    }

    void setLoadingStatus(boolean isLoading){
        this.isLoading.postValue(isLoading);
    }

    MutableLiveData<List<String>> getDateList(){
        return list;
    }

    void setDateList(List<String> list){
        this.list.postValue(list);
    }

    MutableLiveData<String> getMessage(){
        return message;
    }

    void setMessage(String message){
        this.message.postValue(message);
    }

    void addDate(){
        setLoadingStatus(true);
        mModel.saveDate(getTimeCurrent(), this);
    }

    private String getTimeCurrent() {
        String timeCurrent;
        Calendar calendar = Calendar.getInstance();
        timeCurrent = calendar.getTime().toString();
        return timeCurrent;
    }

    @Override
    public void onResponse(Object object) {
        setLoadingStatus(false);
        setDateList((List<String>) object);
    }

    @Override
    public void onError(String error) {
        setMessage(error);
    }

}
