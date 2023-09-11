package com.gsm.networking.data.local.datasource

import com.gsm.networking.data.local.preference.AuthPreference
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private val authPreference: AuthPreference,
) : AuthLocalDataSource {
}