package com.gsm.networking.data.repository

import com.gsm.networking.data.local.datasource.AuthLocalDataSource
import com.gsm.networking.data.remote.datasource.AuthRemoteDataSource
import com.gsm.networking.data.remote.response.toSaveEntity
import com.gsm.networking.domain.entity.SignInEntity
import com.gsm.networking.domain.repository.AuthRepository
import java.time.LocalDateTime
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource
) : AuthRepository {
    override suspend fun signIn(code: String): SignInEntity =
        authRemoteDataSource.signIn(code = code).toSaveEntity(authLocalDataSource)

    override suspend fun refresh(): SignInEntity? {
        return if (LocalDateTime.now().isBefore(authLocalDataSource.fetchRefreshExpiredAt() ?: return null)) {
            authRemoteDataSource.refresh(
                refreshToken = authLocalDataSource.fetchRefreshToken() ?: return null
            ).toSaveEntity(authLocalDataSource)
        } else {
            null
        }
    }
}