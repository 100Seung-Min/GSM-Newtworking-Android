package com.gsm.networking.data.remote.response

import com.google.gson.annotations.SerializedName

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