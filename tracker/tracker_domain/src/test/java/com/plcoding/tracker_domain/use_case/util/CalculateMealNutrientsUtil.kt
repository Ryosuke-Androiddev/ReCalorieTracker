package com.plcoding.tracker_domain.use_case.util

import com.plcoding.core.domain.model.ActivityLevel
import com.plcoding.core.domain.model.Gender
import com.plcoding.core.domain.model.GoalType
import com.plcoding.core.domain.model.UserInfo
import kotlin.math.roundToInt

// Testに関係のない処理は別ファイルから呼び出したほうがいい??
private fun bmr(userInfo: UserInfo): Int {
    return when(userInfo.gender) {
        is Gender.Male -> {
            (66.47f + 13.75f * userInfo.weight +
                    5f * userInfo.height - 6.75f * userInfo.age).roundToInt()
        }
        is Gender.Female ->  {
            (665.09f + 9.56f * userInfo.weight +
                    1.84f * userInfo.height - 4.67 * userInfo.age).roundToInt()
        }
    }
}

fun dailyCaloryRequirement(userInfo: UserInfo): Int {
    val activityFactor = when(userInfo.activityLevel) {
        is ActivityLevel.Low -> 1.2f
        is ActivityLevel.Medium -> 1.3f
        is ActivityLevel.High -> 1.4f
    }
    val caloryExtra = when(userInfo.goalType) {
        is GoalType.LoseWeight -> -500
        is GoalType.KeepWeight -> 0
        is GoalType.GainWeight -> 500
    }
    return (bmr(userInfo) * activityFactor + caloryExtra).roundToInt()
}