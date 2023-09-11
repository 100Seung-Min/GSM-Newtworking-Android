package com.gsm.networking.data.remote.util

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.gsm.networking.domain.exception.ConflictDataException
import com.gsm.networking.domain.exception.InvalidTokenException
import com.gsm.networking.domain.exception.NotFoundException
import com.gsm.networking.domain.exception.ServerErrorException
import com.gsm.networking.domain.exception.TooManyRequestException
import com.gsm.networking.domain.exception.UnKnownHttpException
import com.gsm.networking.domain.exception.WrongDataException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

data class Error(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?,
)

suspend inline fun <T> safeAPICall ( crossinline callFunction: suspend () -> T,
    ): T =
    try {
        withContext(Dispatchers.IO) {
            callFunction()
        }
    } catch (e: HttpException) {
        val error = e.getError() ?: Error("", e.code())
        throw when (error.status) {
            400 -> WrongDataException(error.message)
            401 -> InvalidTokenException(error.message)
            404 -> NotFoundException(error.message)
            409 -> ConflictDataException(error.message)
            429 -> TooManyRequestException(error.message)
            in 500..600 -> ServerErrorException(error.message)
            else -> UnKnownHttpException(error.message)
        }
    }

    fun <T> Response<T>.errorHandling() {
        if (!isSuccessful) {
            throw when (code()) {
                400 -> WrongDataException(message())
                401 -> InvalidTokenException(message())
                404 -> NotFoundException(message())
                409 -> ConflictDataException(message())
                429 -> TooManyRequestException(message())
                in 500..600 -> ServerErrorException(message())
                else -> UnKnownHttpException(message())
            }
        }
    }

    fun HttpException.getError(): Error? =
        response()?.errorBody()?.let { Gson().fromJson(it.string(), Error::class.java) }