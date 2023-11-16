package com.gsm.networking.remote.datasource

import com.gsm.networking.data.remote.datasource.AuthRemoteDataSource
import com.gsm.networking.data.remote.response.SignInResponse
import com.gsm.networking.data.remote.util.safeAPICall
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authAPI: com.gsm.networking.remote.api.AuthAPI
) : AuthRemoteDataSource {
    override suspend fun signIn(code: String): SignInResponse = safeAPICall {
        authAPI.signIn(code = code)
    }

    override suspend fun refresh(refreshToken: String): SignInResponse = safeAPICall {
        authAPI.refresh(refreshToken = "Bearer $refreshToken")
    }
}