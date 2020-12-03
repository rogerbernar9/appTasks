package com.example.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.listener.ValidationListener
import com.example.tasks.service.model.PriorityModel
import com.example.tasks.service.model.TaskModel
import com.example.tasks.service.repository.TaskRepository

class AllTasksViewModel(application: Application) : AndroidViewModel(application) {

    private val mTaskrepository = TaskRepository(application)

    private val mValidation = MutableLiveData<ValidationListener>()
    var validation: LiveData<ValidationListener> = mValidation

    private val mList = MutableLiveData<List<TaskModel>>()

    var tasks: LiveData<List<TaskModel>> = mList


    fun list() {
        mTaskrepository.all(object: APIListener<List<TaskModel>>{
            override fun onSuccess(model: List<TaskModel>) {
                mList.value = model
            }

            override fun onFailure(str: String) {
                mList.value = arrayListOf()
                mValidation.value = ValidationListener(str)
            }

        })

    }

    fun delete(id: Int) {
        mTaskrepository.delete(id, object: APIListener<Boolean> {
            override fun onSuccess(model: Boolean) {
                list()
            }
            override fun onFailure(str: String) {

            }
        })
    }

    fun complete(id: Int) {
        updateStatus(id, true)
    }

    fun undo(id: Int) {
        updateStatus(id, false)
    }

    private fun updateStatus(id: Int, complete: Boolean) {
        mTaskrepository.updateStatus(id, complete, object: APIListener<Boolean> {
            override fun onSuccess(model: Boolean) {
                list()
                mValidation.value = ValidationListener()
            }

            override fun onFailure(str: String) {
                mValidation.value = ValidationListener(str)
            }

        })

    }


}