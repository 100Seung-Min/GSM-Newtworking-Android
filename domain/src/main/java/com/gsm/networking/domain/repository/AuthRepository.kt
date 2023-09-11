package com.gsm.networking.domain.repository

interface AuthRepository {
    suspend fun signIn(code: String)
}