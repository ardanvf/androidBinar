package com.irfan.challenge7.domain.usecase.profile

import com.irfan.challenge7.data.UserRepository
import javax.inject.Inject

class DeleteAllUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() {
        return userRepository.deleteAllUser()
    }
}