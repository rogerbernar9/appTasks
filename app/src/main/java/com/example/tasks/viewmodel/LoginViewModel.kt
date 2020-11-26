package com.example.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.repository.HeaderModel
import com.example.tasks.service.repository.PersonRepository
import retrofit2.Call

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    public val mPersonRepository = PersonRepository()

    /**
     * Faz login usando API
     */
    fun doLogin(email: String, password: String) {
        mPersonRepository.login(email, password, object: APIListener {
            override fun onSuccess(model: HeaderModel) {
                val s = ""
            }

            override fun onFailure(str: String) {
                val s = ""
            }

        })
    }

    /**
     * Verifica se usuário está logado
     */
    fun verifyLoggedUser() {
    }

}