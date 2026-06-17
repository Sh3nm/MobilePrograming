package com.example.gymmembershipapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymmembershipapp.domain.WorkoutType
import com.example.gymmembershipapp.ui.theme.AccentGreen
import com.example.gymmembershipapp.ui.theme.DividerColor
import com.example.gymmembershipapp.ui.theme.SurfaceElevated
import com.example.gymmembershipapp.ui.theme.TextPrimary

@Composable
fun WorkoutTypeChip(
    type: WorkoutType,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bg by animateColorAsState(
        targetValue = if (selected) AccentGreen else SurfaceElevated,
        label = "chipBg"
    )
    val content = if (selected) Color.Black else TextPrimary
    val border: BorderStroke =
        if (selected) BorderStroke(0.dp, AccentGreen) else BorderStroke(1.dp, DividerColor)

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(bg)
            .border(border, RoundedCornerShape(24.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(text = type.emoji, fontSize = 14.sp)
        Text(
            text = type.label,
            color = content,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
    }
}
