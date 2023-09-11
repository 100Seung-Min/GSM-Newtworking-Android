package com.gsm.networking.data.remote.datasource

import com.gsm.networking.data.remote.api.AuthAPI
import com.gsm.networking.data.remote.response.SignInResponse
import com.gsm.networking.data.remote.util.safeAPICall
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authAPI: AuthAPI
) : AuthRemoteDataSource {
    override suspend fun signIn(code: String): SignInResponse = safeAPICall {
        authAPI.signIn(code = code)
    }
}