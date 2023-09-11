package com.gsm.networking.domain.repository

import com.gsm.networking.domain.entity.SignInEntity

interface AuthRepository {
    suspend fun signIn(code: String): SignInEntity

    suspend fun refresh(): SignInEntity?
}