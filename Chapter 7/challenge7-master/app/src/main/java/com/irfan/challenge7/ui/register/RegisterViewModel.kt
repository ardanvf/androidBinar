package com.irfan.challenge7.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfan.challenge7.data.source.local.entity.UserEntity
import com.irfan.challenge7.domain.usecase.register.RegisterUserUseCase
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    fun registerUser(userEntity: UserEntity) {
        viewModelScope.launch {
            registerUserUseCase(userEntity)
        }
    }
}