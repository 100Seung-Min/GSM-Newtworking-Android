package com.gsm.networking.remote.api

import com.gsm.networking.data.remote.response.SignInResponse
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthAPI {
    @POST("auth/signin/google")
    suspend fun signIn(
        @Query("code") code: String
    ): SignInResponse

    @PATCH("auth/reissue")
    suspend fun refresh(
        @Header("refreshToken") refreshToken: String
    ): SignInResponse
}