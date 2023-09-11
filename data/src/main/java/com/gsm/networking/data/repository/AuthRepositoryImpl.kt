package com.gsm.networking.data.repository

import com.gsm.networking.data.remote.datasource.AuthRemoteDataSource
import com.gsm.networking.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {
}