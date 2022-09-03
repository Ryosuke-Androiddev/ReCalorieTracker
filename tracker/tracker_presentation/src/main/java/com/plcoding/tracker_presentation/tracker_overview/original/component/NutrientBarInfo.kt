package com.plcoding.tracker_presentation.tracker_overview.original.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plcoding.tracker_presentation.R
import com.plcoding.tracker_presentation.components.UnitDisplay

@Composable
fun NutrientBarInfo(
    modifier: Modifier = Modifier,
    value: Int,
    goal: Int,
    name: String,
    color: Color,
    strokeWidth: Dp = 8.dp
) {
    val background = MaterialTheme.colors.background
    val goalExceededColor = MaterialTheme.colors.error
    val angleRatio = remember {
        Animatable(0f)
    }

    // ここで受け取ったvalueに変化させてる
    LaunchedEffect(key1 = value) {
        angleRatio.animateTo(
            targetValue = if (goal > 0) {
                value / goal.toFloat()
            } else {
                0f
            },
            animationSpec = tween(
                durationMillis = 300
            )
        )
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // 指定されたアスペクト比に一致するようにサイズが調整される
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            // これが各項目の円グラフ
            drawArc(
                color = if (value <= goal) background else goalExceededColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                size = size,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
            // 円グラフの要素の満たし方
            // 表示する市内をこうやって制御するの新しい感覚
            if (value <= goal) {
                // 90fからスタートするので、sweepAngleをこうしないと全部を囲うことができないのか
                drawArc(
                    color = color,
                    startAngle = 90f,
                    sweepAngle = 360f * angleRatio.value,
                    useCenter = false,
                    size = size,
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap = StrokeCap.Round
                    )
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UnitDisplay(
                amount = value,
                unit = stringResource(id = R.string.grams),
                amountColor = if(value <= goal) {
                    MaterialTheme.colors.onPrimary
                } else goalExceededColor,
                unitColor = if(value <= goal) {
                    MaterialTheme.colors.onPrimary
                } else goalExceededColor
            )
            Text(
                text = name,
                color = if(value <= goal) {
                    MaterialTheme.colors.onPrimary
                } else goalExceededColor,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Light
            )
        }
    }
}