package com.irfan.challenge7.domain.usecase.login

import androidx.lifecycle.LiveData
import com.irfan.challenge7.data.UserRepository
import com.irfan.challenge7.data.source.local.entity.UserEntity
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(email: String, password: String): LiveData<UserEntity?> {
        return userRepository.login(email, password)
    }
}