package com.gsm.networking.remote.di

import com.gsm.networking.remote.BuildConfig
import com.gsm.networking.remote.api.AuthAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideOkHttpclient(): OkHttpClient = OkHttpClient.Builder()
        .build()

    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideAuthAPI(retrofit: Retrofit): AuthAPI = retrofit.create(
        AuthAPI::class.java
    )
}