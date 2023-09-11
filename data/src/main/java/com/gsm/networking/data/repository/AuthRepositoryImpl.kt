package com.gsm.networking.data.repository

import com.gsm.networking.data.local.datasource.AuthLocalDataSource
import com.gsm.networking.data.remote.datasource.AuthRemoteDataSource
import com.gsm.networking.data.remote.response.toEntity
import com.gsm.networking.domain.entity.SignInEntity
import com.gsm.networking.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource
) : AuthRepository {
    override suspend fun signIn(code: String): SignInEntity {
        authRemoteDataSource.signIn(code = code).let {
            with(authLocalDataSource) {
                saveAccessToken(it.accessToken)
                saveRefreshToken(it.refreshToken)
                saveAccessExpiredAt(it.accessTokenExp)
                saveRefreshExpiredAt(it.refreshTokenExp)
            }
            return it.toEntity()
        }
    }
}