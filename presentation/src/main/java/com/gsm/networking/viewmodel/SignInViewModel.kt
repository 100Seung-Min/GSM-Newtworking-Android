package com.gsm.networking.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsm.networking.domain.usecase.auth.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {
    private val _loginState = MutableLiveData(false)
    val loginState: LiveData<Boolean> = _loginState

    fun signIn(code: String) = viewModelScope.launch {
        signInUseCase(code = code).onSuccess {
            _loginState.value = true
        }.onFailure {
        }
    }
}