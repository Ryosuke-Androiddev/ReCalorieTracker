package com.plcoding.core.domain.use_case.model.resource

import com.plcoding.core.util.UiText

sealed class Result {

    data class Success(
        val carbsRatio: Float,
        val proteinRatio: Float,
        val fatRatio: Float
    ): Result()
    data class Error(val message: UiText): Result()
}