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
        val entity = authRemoteDataSource.signIn(code = code).toEntity()
        with(authLocalDataSource) {
            saveAccessToken(entity.accessToken)
            saveRefreshToken(entity.refreshToken)
            saveAccessExpiredAt(entity.accessTokenExp)
            saveRefreshExpiredAt(entity.refreshTokenExp)
        }
        return entity
    }
}