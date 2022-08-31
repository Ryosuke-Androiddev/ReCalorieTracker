package com.plcoding.tracker_domain.use_case.ordinal

import com.plcoding.tracker_domain.model.TrackedFood
import com.plcoding.tracker_domain.repository.TrackerRepository

class DeleteTrackedFoodUseCase(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(food: TrackedFood) {
        repository.deleteTrackedFood(food = food)
    }
}