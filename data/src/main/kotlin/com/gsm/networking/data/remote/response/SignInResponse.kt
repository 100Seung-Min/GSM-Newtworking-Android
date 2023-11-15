package com.gsm.networking.data.remote.response

import com.google.gson.annotations.SerializedName
import com.gsm.networking.data.local.datasource.AuthLocalDataSource
import com.gsm.networking.domain.entity.SignInEntity

data class SignInResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("accessTokenExp")
    val accessTokenExp: String,
    @SerializedName("refreshTokenExp")
    val refreshTokenExp: String,
)

fun SignInResponse.toSaveEntity(authLocalDataSource: AuthLocalDataSource): SignInEntity {
    val entity = SignInEntity(
        accessToken = accessToken,
        refreshToken = refreshToken,
        accessTokenExp = accessTokenExp,
        refreshTokenExp = refreshTokenExp
    )
    with(authLocalDataSource) {
        saveAccessToken(entity.accessToken)
        saveRefreshToken(entity.refreshToken)
        saveAccessExpiredAt(entity.accessTokenExp)
        saveRefreshExpiredAt(entity.refreshTokenExp)
    }
    return entity
}