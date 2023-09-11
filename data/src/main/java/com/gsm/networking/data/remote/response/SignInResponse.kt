package com.gsm.networking.data.remote.response

import com.google.gson.annotations.SerializedName
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

fun SignInResponse.toEntity() = SignInEntity(
    accessToken = accessToken,
    refreshToken = refreshToken,
    accessTokenExp = accessTokenExp,
    refreshTokenExp = refreshTokenExp
)