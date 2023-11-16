package com.gsm.networking.local.di

import com.gsm.networking.data.local.preference.AuthPreference
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {
    @Binds
    abstract fun bindAuthPreference(
        authPreferenceImpl: com.gsm.networking.local.preference.AuthPreferenceImpl
    ): AuthPreference
}