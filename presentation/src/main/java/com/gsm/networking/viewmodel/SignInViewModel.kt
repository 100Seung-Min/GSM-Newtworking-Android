package com.gsm.networking.viewmodel

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
    fun signIn(code: String) = viewModelScope.launch {
        signInUseCase(code = code).onSuccess {
            println("안녕 $it")
        }.onFailure {
            println("안녕 $it")
        }
    }
}