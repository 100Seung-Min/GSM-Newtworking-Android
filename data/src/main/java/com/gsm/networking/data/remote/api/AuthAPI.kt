package com.gsm.networking.data.remote.api

import com.gsm.networking.data.remote.response.SignInResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthAPI {
    @POST("auth/signin/google")
    suspend fun signIn(
        @Query("code") code: String
    ): SignInResponse
}