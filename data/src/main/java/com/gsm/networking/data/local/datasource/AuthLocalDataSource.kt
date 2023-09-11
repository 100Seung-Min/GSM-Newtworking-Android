package com.gsm.networking.data.local.datasource

import java.time.LocalDateTime

interface AuthLocalDataSource {
    fun saveAccessToken(accessToken: String)

    fun fetchAccessToken(): String?

    fun saveRefreshToken(refreshToken: String)

    fun fetchRefreshToken(): String?

    fun saveAccessExpiredAt(accessExpiredAt: String)

    fun fetchAccessExpiredAt(): LocalDateTime?

    fun saveRefreshExpiredAt(refreshExpiredAt: String)

    fun fetchRefreshExpiredAt(): LocalDateTime?

    fun clearToken()
}