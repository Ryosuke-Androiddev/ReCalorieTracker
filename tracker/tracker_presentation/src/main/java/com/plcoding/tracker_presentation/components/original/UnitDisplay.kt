package com.plcoding.tracker_presentation.components.original

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.plcoding.core_ui.LocalSpacing

@Composable
fun UnitDisplay(
    modifier: Modifier = Modifier,
    amount: Int,
    unit: String,
    amountTextSize: TextUnit = 20.sp,
    amountTextColor: Color = MaterialTheme.colors.onBackground,
    unitTextSize: TextUnit = 14.sp,
    unitTextColor: Color = MaterialTheme.colors.onBackground
) {
    val spacing = LocalSpacing.current
    Row(modifier = modifier) {
        Text(
            modifier = modifier.alignBy(LastBaseline),
            text = amount.toString(),
            fontSize = amountTextSize,
            color = amountTextColor,
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
        Text(
            modifier = modifier.alignBy(LastBaseline),
            text = unit,
            fontSize = unitTextSize,
            color = unitTextColor,
            style = MaterialTheme.typography.h1
        )
    }
}