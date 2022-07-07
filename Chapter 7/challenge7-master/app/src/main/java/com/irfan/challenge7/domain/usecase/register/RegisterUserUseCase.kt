package com.irfan.challenge7.domain.usecase.register

import com.irfan.challenge7.data.UserRepository
import com.irfan.challenge7.data.source.local.entity.UserEntity
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userEntity: UserEntity) {
        return userRepository.insertUser(userEntity)
    }
}