package com.gsm.networking.di

import com.gsm.networking.data.local.preference.AuthPreference
import com.gsm.networking.data.local.preference.AuthPreferenceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {
    @Binds
    abstract fun provideAuthPreference(
        authPreferenceImpl: AuthPreferenceImpl
    ): AuthPreference
}