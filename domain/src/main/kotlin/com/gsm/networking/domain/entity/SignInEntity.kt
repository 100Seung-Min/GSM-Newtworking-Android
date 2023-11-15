package com.gsm.networking.domain.entity

data class SignInEntity(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExp: String,
    val refreshTokenExp: String,
)