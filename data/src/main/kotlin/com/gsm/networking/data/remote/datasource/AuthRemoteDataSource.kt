package com.gsm.networking.data.remote.datasource

import com.gsm.networking.data.remote.response.SignInResponse

interface AuthRemoteDataSource {
    suspend fun signIn(code: String): SignInResponse

    suspend fun refresh(refreshToken: String): SignInResponse
}