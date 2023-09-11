package com.gsm.networking.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsm.networking.domain.entity.SignInEntity
import com.gsm.networking.domain.usecase.auth.RefreshUseCase
import com.gsm.networking.domain.usecase.auth.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val refreshUseCase: RefreshUseCase
) : ViewModel() {
    private val _signInEntity = MutableLiveData<SignInEntity>()
    val signInEntity: LiveData<SignInEntity> = _signInEntity

    init {
        viewModelScope.launch {
            refreshUseCase().onSuccess {
                it?.let { _signInEntity.value = it }
            }.onFailure {
            }
        }
    }

    fun signIn(code: String) = viewModelScope.launch {
        signInUseCase(code = code).onSuccess {
            _signInEntity.value = it
        }.onFailure {
        }
    }
}