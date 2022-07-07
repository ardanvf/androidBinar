package com.irfan.challenge7.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.irfan.challenge7.data.source.local.entity.UserEntity
import com.irfan.challenge7.domain.usecase.profile.DeleteAllMovieUseCase
import com.irfan.challenge7.domain.usecase.profile.DeleteAllUserUseCase
import com.irfan.challenge7.domain.usecase.profile.GetUserUseCase
import com.irfan.challenge7.domain.usecase.profile.UpdateUserUseCase
import com.irfan.challenge7.storage.PreferencesManager
import kotlinx.coroutines.launch

class ProfileViewModel(
    val getUserUseCase: GetUserUseCase,
    val updateUserUseCase: UpdateUserUseCase,
    val deleteAllUserUseCase: DeleteAllUserUseCase,
    val deleteAllMovieUseCase: DeleteAllMovieUseCase,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    fun getUserEmail(): LiveData<String> {
        return preferencesManager.getUserEmail().asLiveData()
    }

    fun getUser(email:String) = getUserUseCase(email)

    fun updateUser(userEntity: UserEntity) {
        viewModelScope.launch {
            updateUserUseCase(userEntity)
        }
    }

    fun deleteAllMovie() {
        viewModelScope.launch {
            deleteAllMovieUseCase()
        }
    }

    fun deleteAllUser() {
        viewModelScope.launch {
            deleteAllUserUseCase()
        }
    }

    fun clearUserLoggedIn() = viewModelScope.launch {
        preferencesManager.clearUserLoggedIn()
    }
}