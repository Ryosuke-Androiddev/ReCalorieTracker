package com.plcoding.onboarding_presentation.nutrient_goal.model.event

sealed class NutrientGoalEvent {

    data class OnCarbRatioEventEnter(val ratio: String): NutrientGoalEvent()
    data class OnProteinRatioEventEnter(val ratio: String): NutrientGoalEvent()
    data class OnFatRatioEventEnter(val ratio: String): NutrientGoalEvent()
    object OnNextClick: NutrientGoalEvent()
}