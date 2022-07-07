package com.irfan.challenge7.domain.usecase.profile

import androidx.lifecycle.LiveData
import com.irfan.challenge7.data.UserRepository
import com.irfan.challenge7.data.source.local.entity.UserEntity
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(email: String): LiveData<UserEntity?> {
        return userRepository.getUser(email)
    }
}