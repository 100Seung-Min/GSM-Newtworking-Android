package com.gsm.networking.domain.usecase.auth

import com.gsm.networking.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(code: String) = kotlin.runCatching {
        authRepository.signIn(code = code)
    }
}