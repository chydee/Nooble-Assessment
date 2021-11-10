package com.chidi.nooble.utils


sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    class Error(val error: Throwable) : Result<Nothing>()
}