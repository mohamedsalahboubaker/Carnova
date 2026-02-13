package com.salah.carnova.domain

data class AuthState(

val isLoading: Boolean = false,
val isSuccess: Boolean = false,
val errorMessage: String? = null
)

