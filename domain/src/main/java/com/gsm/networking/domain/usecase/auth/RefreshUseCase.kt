package com.gsm.networking.domain.usecase.auth

import com.gsm.networking.domain.repository.AuthRepository
import javax.inject.Inject

class RefreshUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        authRepository.refresh()
    }
}