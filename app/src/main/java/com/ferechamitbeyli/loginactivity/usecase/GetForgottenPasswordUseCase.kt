package com.ferechamitbeyli.loginactivity.usecase

import com.ferechamitbeyli.loginactivity.ui.auth.states.LoginErrorState
import timber.log.Timber
import javax.inject.Inject

open class GetForgottenPasswordUseCase @Inject constructor(
    private val getUserByEmailUseCase: GetUserByEmailUseCase
) {

    open suspend operator fun invoke(email: String): Result {
        Timber.d(email)
        return try {
            Result.Success(getUserByEmailUseCase(email).password)
        } catch (e: Exception) {
            Timber.d("Error in restorePassword(), ${e.message}")
            Result.Failure
        }
    }

    sealed class Result {
        data class Success(val password: String) : Result()
        object Failure : Result()
    }

}