package com.gsm.networking.data.local.preference

import android.content.SharedPreferences
import javax.inject.Inject

class AuthPreferenceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : AuthPreference {
}