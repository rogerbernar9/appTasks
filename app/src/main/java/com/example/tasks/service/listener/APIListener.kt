package com.example.tasks.service.listener

import com.example.tasks.service.repository.HeaderModel

interface APIListener {

    fun onSuccess(model: HeaderModel)

    fun onFailure(str: String)

}