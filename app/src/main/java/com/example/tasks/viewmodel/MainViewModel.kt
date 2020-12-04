package com.example.tasks.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.listener.ValidationListener
import com.example.tasks.service.repository.local.SecurityPreferences

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val mSharedPreferences = SecurityPreferences(application)

    private val mUserName = MutableLiveData<String>()
    var username: LiveData<String> = mUserName

    fun loadUserName() {
        mUserName.value = mSharedPreferences
            .get(TaskConstants.SHARED.PERSON_NAME)
    }

}