package com.plcoding.tracker_domain.use_case.ordinal.model

import com.plcoding.tracker_domain.use_case.DeleteTrackedFood
import com.plcoding.tracker_domain.use_case.GetFoodsForDate
import com.plcoding.tracker_domain.use_case.SearchFood
import com.plcoding.tracker_domain.use_case.ordinal.CalculateMealNutrients
import com.plcoding.tracker_domain.use_case.ordinal.InsertTrackedFood

data class UseCase(
    val searchFood: SearchFood,
    val getFoodsForDate: GetFoodsForDate,
    val insertTrackedFood: InsertTrackedFood,
    val deleteTrackedFood: DeleteTrackedFood,
    val calculateMealNutrients: CalculateMealNutrients
)
