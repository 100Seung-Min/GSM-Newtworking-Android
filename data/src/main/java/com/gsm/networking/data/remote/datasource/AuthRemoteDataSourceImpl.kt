package com.gsm.networking.data.remote.datasource

import com.gsm.networking.data.remote.api.AuthAPI
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authAPI: AuthAPI
) : AuthRemoteDataSource {
}