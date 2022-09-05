package com.plcoding.tracker_domain.use_case

import com.google.common.truth.Truth.assertThat
import com.plcoding.core.domain.model.ActivityLevel
import com.plcoding.core.domain.model.Gender
import com.plcoding.core.domain.model.GoalType
import com.plcoding.core.domain.model.UserInfo
import com.plcoding.core.domain.preferences.Preferences
import com.plcoding.tracker_domain.model.MealType
import com.plcoding.tracker_domain.model.TrackedFood
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random

class CalculateMealNutrientsTest {

    private lateinit var calculateMealNutrients: CalculateMealNutrients

    @Before
    fun setup() {
        // 毎回この中のオブジェクトが生成される
        val preferences = mockk<Preferences>(relaxed = true)
        // ここで呼び出してしまうっていうのが新しい
        every {
            preferences.loadUserInfo()
        } returns UserInfo(
            gender = Gender.Male,
            age = 20,
            weight = 80f,
            height = 180,
            activityLevel = ActivityLevel.Medium,
            goalType = GoalType.KeepWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f
        )
        // mockで差し替える
        calculateMealNutrients = CalculateMealNutrients(preferences = preferences)
    }

    // 計算が正しい時とそうでないテストケースも作成すべき??
    // 計算が正しくいかないということも示すべき
    @Test
    fun `Calories for Breakfast properly calculated`() {

        // UseCaseに計算させるダミーのデータを定義
        val dummyTrackedFood = (1..30).map {
            TrackedFood(
                name = "dummy + $it",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random()
                ),
                imageUrl = null,
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(2000)
            )
        }

        // 実際にここで処理をコール
        val result = calculateMealNutrients(trackedFoods = dummyTrackedFood)

        val breakFastCalories = result.mealNutrients.values
            .filter { it.mealType == MealType.Breakfast }
            .sumOf { it.calories }

        val expectedCalories = dummyTrackedFood
            .filter { it.mealType == MealType.Breakfast }
            .sumOf { it.calories }

        assertThat(breakFastCalories).isEqualTo(expectedCalories)
    }

    @Test
    fun `Calories for Lunch properly calculated`() {
        val dummyTrackedFood = (1..30).map {
            TrackedFood(
                name = "dummy + $it",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random()
                ),
                imageUrl = null,
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(2000)
            )
        }

        // 実際にここで処理をコール
        val result = calculateMealNutrients(trackedFoods = dummyTrackedFood)

        val breakFastCalories = result.mealNutrients.values
            .filter { it.mealType == MealType.Lunch }
            .sumOf { it.calories }

        val expectedCalories = dummyTrackedFood
            .filter { it.mealType == MealType.Lunch }
            .sumOf { it.calories }

        assertThat(breakFastCalories).isEqualTo(expectedCalories)
    }

    @Test
    fun `Calories for Dinner properly calculated`() {
        val dummyTrackedFood = (1..30).map {
            TrackedFood(
                name = "dummy + $it",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random()
                ),
                imageUrl = null,
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(2000)
            )
        }

        // 実際にここで処理をコール
        val result = calculateMealNutrients(trackedFoods = dummyTrackedFood)

        val breakFastCalories = result.mealNutrients.values
            .filter { it.mealType == MealType.Dinner }
            .sumOf { it.calories }

        val expectedCalories = dummyTrackedFood
            .filter { it.mealType == MealType.Dinner }
            .sumOf { it.calories }

        assertThat(breakFastCalories).isEqualTo(expectedCalories)
    }

    @Test
    fun `Calories for Snack properly calculated`() {
        val dummyTrackedFood = (1..30).map {
            TrackedFood(
                name = "dummy + $it",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random()
                ),
                imageUrl = null,
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(2000)
            )
        }

        // 実際にここで処理をコール
        val result = calculateMealNutrients(trackedFoods = dummyTrackedFood)

        val breakFastCalories = result.mealNutrients.values
            .filter { it.mealType == MealType.Snack }
            .sumOf { it.calories }

        val expectedCalories = dummyTrackedFood
            .filter { it.mealType == MealType.Snack }
            .sumOf { it.calories }

        assertThat(breakFastCalories).isEqualTo(expectedCalories)
    }

    // 正常系がうまくいけば、ロジックがうまくいっているはずなので、エラーケースは一つでもよいかも
    @Test
    fun `Calories for Breakfast doesn't properly calculated`() {
        val dummyTrackedFood = (1..30).map {
            TrackedFood(
                name = "dummy + $it",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random()
                ),
                imageUrl = null,
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(2000)
            )
        }

        // 実際にここで処理をコール
        val result = calculateMealNutrients(trackedFoods = dummyTrackedFood)

        val breakFastCalories = result.mealNutrients.values
            .filter { it.mealType == MealType.Breakfast }
            .sumOf { it.calories }

        // 合計をする際に間違えることはほぼないと思うけど
        val expectedCalories = dummyTrackedFood
            .filter { it.mealType == MealType.Breakfast }
            .sumOf { it.protein }

        assertThat(breakFastCalories).isNotEqualTo(expectedCalories)
    }
}